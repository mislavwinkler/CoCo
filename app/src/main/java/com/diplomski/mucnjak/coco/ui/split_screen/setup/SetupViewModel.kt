package com.diplomski.mucnjak.coco.ui.split_screen.setup

import androidx.lifecycle.viewModelScope
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
) : BaseViewModel<SetupState, SetupNavigationEvent>(SetupState.Initial) {

    init {
        viewModelScope.launch {
            launch {
                subscribeToNavigationState().collect {
                    if (it == State.WELCOME) {
                        setNavigationEvent(SetupNavigationEvent.NavigateToWelcomeScreen)
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
        viewModelScope.launch {
            confirmNextStep(studentIndex = studentIndex)
        }
    }

    fun rotateScreen(studentIndex: Int) {
        viewModelScope.launch {
            rotateStudentScreen(studentIndex)
        }
    }
}