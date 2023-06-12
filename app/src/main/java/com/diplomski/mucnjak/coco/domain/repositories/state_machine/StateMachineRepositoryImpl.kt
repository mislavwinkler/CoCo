package com.diplomski.mucnjak.coco.domain.repositories.state_machine

import com.diplomski.mucnjak.coco.domain.repositories.active_activity.ActiveActivityRepository
import com.diplomski.mucnjak.coco.domain.repositories.analytics.AnalyticsRepository
import com.diplomski.mucnjak.coco.domain.repositories.answer_checker.AnswerCheckerRepository
import com.diplomski.mucnjak.coco.domain.repositories.clock.ClockRepository
import com.diplomski.mucnjak.coco.domain.repositories.iteration.IterationRepository
import com.diplomski.mucnjak.coco.shared.dispatcher.Dispatcher
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.BufferOverflow
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
    private val answerCheckerRepository: AnswerCheckerRepository,
    private val analyticsRepository: AnalyticsRepository,
    private val iterationRepository: IterationRepository,
) : StateMachineRepository {

    private var state: State = State.NAME_INPUT
    private val studentConfirmation: MutableMap<Int, Boolean> = mutableMapOf()

    private val stateFlow: MutableSharedFlow<State> = MutableSharedFlow(
        replay = 0,
        onBufferOverflow = BufferOverflow.DROP_OLDEST,
        extraBufferCapacity = 1
    )
    override val navigate: Flow<State> = stateFlow

    private val mutex = Mutex()

    @OptIn(ExperimentalCoroutinesApi::class)
    override suspend fun reset() {
        state = State.NAME_INPUT
        iterationRepository.reset()
        stateFlow.resetReplayCache()
        studentConfirmation.clear()
        repeat(activeActivityRepository.getActiveActivity().activeNumOfStudents) {
            studentConfirmation[it] = false
        }
    }

    override suspend fun confirmNextStep(studentIndex: Int): Unit = withContext(dispatcher.io) {
        mutex.withLock {
            studentConfirmation[studentIndex] = true
            if (studentConfirmation.values.all { it }) {
                nextStep()
            }
        }
    }

    override suspend fun revokeNextStepConfirmation(studentIndex: Int): Unit =
        withContext(dispatcher.io) {
            mutex.withLock {
                studentConfirmation[studentIndex] = false
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
        studentConfirmation.replaceAll { _, _ -> false }
        clockRepository.cancelTimer()
        state = when (state) {
            State.NAME_INPUT -> startSetupStep()
            State.SETUP -> State.WELCOME
            State.WELCOME -> startSolvingStep()
            State.SOLVING -> determineStepAfterSolving()
            State.INCORRECT_SOLUTION_NOTE -> startDiscussionStep()
            State.DISCUSSION -> startRetryNoteStep()
            State.RETRY_NOTE -> startSolvingStep()
            State.FINISH_NOTE -> State.SOLUTIONS
            State.SOLUTIONS -> State.WELCOME
        }
        stateFlow.emit(state)
    }

    private fun startSetupStep(): State {
        analyticsRepository.init()
        return State.SETUP
    }

    private suspend fun startSolvingStep(): State {
        initStepTimer(State.SOLVING)
        return State.SOLVING
    }

    private suspend fun startDiscussionStep(): State {
        initStepTimer(State.DISCUSSION)
        return State.DISCUSSION
    }

    private fun startRetryNoteStep(): State {
        analyticsRepository.storeDiscussionTime()
        return State.RETRY_NOTE
    }

    @OptIn(DelicateCoroutinesApi::class)
    private suspend fun startFinishNote(): State {
        GlobalScope.launch {
            analyticsRepository.postAnalytics()
            clockRepository.startClock(4) { nextStep() }
        }
        return State.FINISH_NOTE
    }

    private suspend fun determineStepAfterSolving(): State {
        analyticsRepository.storeResolutionTimeout()
        analyticsRepository.calculateAndStoreAccuracies()
        return if (answerCheckerRepository.checkAnswers() || !hasNextStep()) {
            startFinishNote()
        } else {
            State.INCORRECT_SOLUTION_NOTE
        }
    }

    private suspend fun initStepTimer(state: State) {
        val activity = activeActivityRepository.getActiveActivity()
        val time = if (state == State.SOLVING && iterationRepository.getCurrentIteration() == -1) {
            val time = activity.solvingTime
            iterationRepository.setCurrentIteration(0)
            time
        } else if (state == State.SOLVING && iterationRepository.getCurrentIteration() >= 0) {
            val time = activity.correctionTimes[iterationRepository.getCurrentIteration()]
            iterationRepository.incrementCurrentIteration()
            time
        } else if (state == State.DISCUSSION) {
            activity.discussionTimes[iterationRepository.getCurrentIteration()]
        } else {
            throw IllegalStateException()
        }
        // Time is in minutes
        clockRepository.startClock(15/*time * 60*/) { nextStep() }
    }

    override suspend fun getNextDisplayTime(): Int = withContext(dispatcher.io) {
        if (state == State.WELCOME) {
            activeActivityRepository.getActiveActivity().solvingTime
        } else if (state == State.INCORRECT_SOLUTION_NOTE && hasNextStep()) {
            activeActivityRepository.getActiveActivity().discussionTimes[iterationRepository.getCurrentIteration()]
        } else {
            throw IllegalStateException()
        }
    }

    private suspend fun hasNextStep(): Boolean {
        val activity = activeActivityRepository.getActiveActivity()
        return activity.discussionTimes.size > iterationRepository.getCurrentIteration()
    }
}
