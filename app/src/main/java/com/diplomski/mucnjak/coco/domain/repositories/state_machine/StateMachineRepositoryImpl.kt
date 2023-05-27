package com.diplomski.mucnjak.coco.domain.repositories.state_machine

import com.diplomski.mucnjak.coco.domain.repositories.active_activity.ActiveActivityRepository
import com.diplomski.mucnjak.coco.domain.repositories.clock.ClockRepository
import com.diplomski.mucnjak.coco.shared.dispatcher.Dispatcher
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class StateMachineRepositoryImpl @Inject constructor(
    private val activeActivityRepository: ActiveActivityRepository,
    private val clockRepository: ClockRepository,
    private val dispatcher: Dispatcher,
) : StateMachineRepository {

    private var state: State = State.NAME_INPUT
    private val studentConfirmation: MutableMap<Int, Boolean> = mutableMapOf()

    private val stateFlow: MutableSharedFlow<State> = MutableSharedFlow()
    override val navigate: Flow<State> = stateFlow

    private var iteration = -1

    private val mutex = Mutex()

    @OptIn(ExperimentalCoroutinesApi::class)
    override suspend fun reset() {
        state = State.NAME_INPUT
        iteration = -1
        stateFlow.resetReplayCache()
        studentConfirmation.clear()
        repeat(activeActivityRepository.getActiveActivity().activeNumOfStudents) {
            studentConfirmation[it] = false
        }
    }

    override suspend fun confirmNextStep(studentIndex: Int) = withContext(dispatcher.io) {
        mutex.withLock {
            studentConfirmation[studentIndex] = true
            if (studentConfirmation.values.all { it }) {
                nextStep()
                studentConfirmation.replaceAll { _, _ -> false }
            }
        }
    }

// NAME INPUT
// SETUP
// WELCOME
// SOLVING
// INCORRECT_SOLUTION_NOTE
// DISCUSSION
// RETRY_NOTE
// FINISH_NOTE
// SOLUTIONS

    private suspend fun nextStep() {
        state = when (state) {
            State.NAME_INPUT -> State.SETUP
            State.SETUP -> State.WELCOME
            State.WELCOME -> startSolvingStep()
            State.SOLVING -> determineStepAfterSolving()
            State.INCORRECT_SOLUTION_NOTE -> startDiscussionStep()
            State.DISCUSSION -> State.RETRY_NOTE
            State.RETRY_NOTE -> State.SOLVING
            State.FINISH_NOTE -> State.SOLUTIONS
            State.SOLUTIONS -> State.WELCOME
        }
        stateFlow.emit(state)
    }

    private suspend fun startSolvingStep(): State {
        initStepTimer(State.SOLVING)
        return State.SOLVING
    }

    private suspend fun startDiscussionStep(): State {
        initStepTimer(State.DISCUSSION)
        return State.DISCUSSION
    }

    private suspend fun determineStepAfterSolving(): State {
        val activity = activeActivityRepository.getActiveActivity()
        // TODO implement answer checker
        if (false && activity.discussionTimes.size <= iteration) {
            return State.FINISH_NOTE
        } else {
            return State.INCORRECT_SOLUTION_NOTE
        }
    }

    private suspend fun initStepTimer(state: State) {
        val activity = activeActivityRepository.getActiveActivity()
        val time = if (state == State.SOLVING && iteration == -1) {
            val time = activity.solvingTime
            iteration = 0
            time
        } else if (state == State.SOLVING && iteration >= 0) {
            val time = activity.correctionTimes[iteration]
            iteration++
            time
        } else if (state == State.DISCUSSION) {
            activity.discussionTimes[iteration]
        } else {
            throw IllegalStateException()
        }
        // Time is in minutes
        clockRepository.startClock(time * 60) { nextStep() }
    }

    override suspend fun getNextDisplayTime(): Int = withContext(dispatcher.io) {
        if (state == State.INCORRECT_SOLUTION_NOTE) {
            activeActivityRepository.getActiveActivity().discussionTimes[iteration]
        } else {
            throw IllegalStateException()
        }
    }
}