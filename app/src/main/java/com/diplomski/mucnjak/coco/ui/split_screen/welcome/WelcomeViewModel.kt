package com.diplomski.mucnjak.coco.ui.split_screen.welcome

import androidx.lifecycle.viewModelScope
import com.diplomski.mucnjak.coco.R
import com.diplomski.mucnjak.coco.analytics.Analytics
import com.diplomski.mucnjak.coco.domain.get_string.GetString
import com.diplomski.mucnjak.coco.domain.repositories.state_machine.State
import com.diplomski.mucnjak.coco.domain.use_case.confirm_next_step.ConfirmNextStep
import com.diplomski.mucnjak.coco.domain.use_case.get_activity.GetActivity
import com.diplomski.mucnjak.coco.domain.use_case.get_available_question.GetAvailableQuestion
import com.diplomski.mucnjak.coco.domain.use_case.get_next_time.GetNextTime
import com.diplomski.mucnjak.coco.domain.use_case.get_student_name.GetStudentName
import com.diplomski.mucnjak.coco.domain.use_case.rotate_student_screen.RotateStudentScreen
import com.diplomski.mucnjak.coco.domain.use_case.subscribe_to_navigation_state.SubscribeToNavigationState
import com.diplomski.mucnjak.coco.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

object NavigateToSolving

@HiltViewModel
class WelcomeViewModel @Inject constructor(
    private val getStudentName: GetStudentName,
    private val getAvailableQuestion: GetAvailableQuestion,
    private val rotateStudentScreen: RotateStudentScreen,
    private val getActivity: GetActivity,
    private val confirmNextStep: ConfirmNextStep,
    private val subscribeToNavigationState: SubscribeToNavigationState,
    private val getString: GetString,
    private val getNextTime: GetNextTime,
    private val analytics: Analytics,
) : BaseViewModel<WelcomeState, NavigateToSolving>(WelcomeState.Initial) {

    init {
        viewModelScope.launch {
            launch {
                subscribeToNavigationState().collect { state ->
                    if (state == State.SOLVING) {
                        setNavigationEvent(NavigateToSolving)
                    }
                }
            }
        }
    }

    fun rotateScreen(studentIndex: Int) {
        analytics.sendStudentRotation(studentIndex, "Welcome")
        viewModelScope.launch {
            rotateStudentScreen(studentIndex = studentIndex)
        }
    }

    fun onConfirmActivityPreview(studentIndex: Int) {
        analytics.sendStudentReady(studentIndex, "Welcome")
        viewModelScope.launch {
            confirmNextStep(studentIndex)
            updateState { state ->
                (state as? WelcomeState.ActivityPreview)?.copy(isConfirmed = true) ?: state
            }
        }
    }

    fun init(studentIndex: Int) {
        viewModelScope.launch {
            val activity = getActivity()
            val question = getAvailableQuestion(studentIndex = studentIndex)
            val solvingTime = getNextTime()

            updateState {
                WelcomeState.ActivityPreview(
                    studentName = getStudentName(studentIndex = studentIndex),
                    topic = activity.topic,
                    subtopic = activity.subTopic,
                    description = getString(
                        R.string.welcome_activity_description,
                        solvingTime,
                        question.questionText
                    )
                )
            }
        }
    }
}