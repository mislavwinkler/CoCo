package com.diplomski.mucnjak.coco.ui.split_screen.retry_note

import androidx.lifecycle.viewModelScope
import com.diplomski.mucnjak.coco.analytics.Analytics
import com.diplomski.mucnjak.coco.domain.use_case.confirm_next_step.ConfirmNextStep
import com.diplomski.mucnjak.coco.domain.use_case.rotate_student_screen.RotateStudentScreen
import com.diplomski.mucnjak.coco.domain.use_case.subscribe_to_navigation_state.SubscribeToNavigationState
import com.diplomski.mucnjak.coco.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RetryNoteViewModel @Inject constructor(
    private val rotateStudentScreen: RotateStudentScreen,
    private val confirmNextStep: ConfirmNextStep,
    private val analytics: Analytics,
    subscribeToNavigationState: SubscribeToNavigationState,
) : BaseViewModel<RetryNoteState, RetryNoteNavigationEvent>(RetryNoteState.Initial()) {

    init {
        viewModelScope.launch {
            subscribeToNavigationState().collect {
                setNavigationEvent(event = RetryNoteNavigationEvent.NavigateToSolving)
            }
        }
    }

    fun rotateScreen(studentIndex: Int) {
        analytics.sendStudentRotation(studentIndex, "Retry note")
        viewModelScope.launch {
            rotateStudentScreen(studentIndex = studentIndex)
        }
    }

    fun confirmStudentNextStep(studentIndex: Int) {
        analytics.sendStudentReady(studentIndex, "Retry note")
        viewModelScope.launch {
            confirmNextStep(studentIndex = studentIndex)
            updateState { state ->
                (state as? RetryNoteState.Initial)?.copy(isConfirmed = true) ?: state
            }
        }
    }
}