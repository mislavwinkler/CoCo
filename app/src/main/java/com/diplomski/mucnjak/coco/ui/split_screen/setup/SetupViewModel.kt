package com.diplomski.mucnjak.coco.ui.split_screen.setup

import androidx.lifecycle.viewModelScope
import com.diplomski.mucnjak.coco.analytics.Analytics
import com.diplomski.mucnjak.coco.domain.repositories.state_machine.State
import com.diplomski.mucnjak.coco.domain.use_case.confirm_next_step.ConfirmNextStep
import com.diplomski.mucnjak.coco.domain.use_case.get_student_name.GetStudentName
import com.diplomski.mucnjak.coco.domain.use_case.rotate_student_screen.RotateStudentScreen
import com.diplomski.mucnjak.coco.domain.use_case.subscribe_to_navigation_state.SubscribeToNavigationState
import com.diplomski.mucnjak.coco.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SetupViewModel @Inject constructor(
    private val getStudentName: GetStudentName,
    private val rotateStudentScreen: RotateStudentScreen,
    private val confirmNextStep: ConfirmNextStep,
    private val subscribeToNavigationState: SubscribeToNavigationState,
    private val analytics: Analytics,
) : BaseViewModel<SetupState, SetupNavigationEvent>(SetupState.Initial) {

    init {
        viewModelScope.launch {
            launch {
                subscribeToNavigationState().collect { navigationEvent ->
                    if (navigationEvent == State.WELCOME) {
                        setNavigationEvent(event = SetupNavigationEvent.NavigateToWelcomeScreen)
                    }
                }
            }
        }
    }

    fun init(studentIndex: Int) {
        updateState {
            SetupState.SetupRotation(studentName = getStudentName(studentIndex))
        }
    }

    fun confirmSetup(studentIndex: Int) {
        analytics.sendStudentReady(studentIndex, "Setup")
        viewModelScope.launch {
            confirmNextStep(studentIndex = studentIndex)
            updateState { state ->
                (state as? SetupState.SetupRotation)?.copy(isConfirmed = true) ?: state
            }
        }
    }

    fun rotateScreen(studentIndex: Int) {
        analytics.sendStudentRotation(studentIndex, "Setup")
        viewModelScope.launch {
            rotateStudentScreen(studentIndex = studentIndex)
        }
    }
}