package com.diplomski.mucnjak.coco.ui.split_screen.discussion

import androidx.lifecycle.viewModelScope
import com.diplomski.mucnjak.coco.domain.repositories.state_machine.State
import com.diplomski.mucnjak.coco.domain.use_case.add_incorrect_answer_info.AddIncorrectAnswerInfo
import com.diplomski.mucnjak.coco.domain.use_case.confirm_next_step.ConfirmNextStep
import com.diplomski.mucnjak.coco.domain.use_case.get_all_answers.GetAllAnswers
import com.diplomski.mucnjak.coco.domain.use_case.get_student_answers.GetStudentAnswers
import com.diplomski.mucnjak.coco.domain.use_case.get_available_question.GetAvailableQuestion
import com.diplomski.mucnjak.coco.domain.use_case.get_student_name.GetStudentName
import com.diplomski.mucnjak.coco.domain.use_case.rotate_student_screen.RotateStudentScreen
import com.diplomski.mucnjak.coco.domain.use_case.subscribe_to_navigation_state.SubscribeToNavigationState
import com.diplomski.mucnjak.coco.domain.use_case.subscribe_to_timer_ticks.SubscribeToTimerTicks
import com.diplomski.mucnjak.coco.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DiscussionViewModel @Inject constructor(
    private val rotateStudentScreen: RotateStudentScreen,
    private val confirmNextStep: ConfirmNextStep,
    private val getStudentName: GetStudentName,
    private val getAvailableQuestion: GetAvailableQuestion,
    private val getAllAnswers: GetAllAnswers,
    private val addIncorrectAnswerInfo: AddIncorrectAnswerInfo,
    private val getStudentAnswers: GetStudentAnswers,
    subscribeToNavigationState: SubscribeToNavigationState,
    subscribeToTimerTicks: SubscribeToTimerTicks,
) : BaseViewModel<DiscussionState, DiscussionNavigationEvent>(DiscussionState.Initial) {

    private var time: Int = 0

    init {
        viewModelScope.launch {

            launch {
                subscribeToNavigationState().collect { state ->
                    if (state == State.RETRY_NOTE) {
                        setNavigationEvent(event = DiscussionNavigationEvent.NavigateToRetry)
                    }
                }
            }

            launch {
                subscribeToTimerTicks().collect { time ->
                    this@DiscussionViewModel.time = time
                    updateState { state ->
                        (state as? DiscussionState.Discussion)?.copy(time = this@DiscussionViewModel.time.toString())
                            ?: state
                    }
                }
            }
        }
    }

    fun rotateScreen(studentIndex: Int) {
        viewModelScope.launch {
            rotateStudentScreen(studentIndex = studentIndex)
        }
    }

    fun confirmStudentNextStep(studentIndex: Int) {
        viewModelScope.launch {
            confirmNextStep(studentIndex = studentIndex)
        }
    }

    fun init(studentIndex: Int) {
        viewModelScope.launch {
            val selectedAnswers = getStudentAnswers(studentIndex)
            val answers = getAllAnswers() - selectedAnswers.toSet()
            val question = getAvailableQuestion(studentIndex = studentIndex)

            updateState {
                DiscussionState.Discussion(
                    studentName = getStudentName(studentIndex),
                    question = question.questionText,
                    answers = addIncorrectAnswerInfo(answers),
                    selectedAnswers = addIncorrectAnswerInfo(selectedAnswers),
                    time = "0"
                )
            }
        }
    }
}