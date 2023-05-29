package com.diplomski.mucnjak.coco.ui.split_screen.incorrect_solution

import androidx.lifecycle.viewModelScope
import com.diplomski.mucnjak.coco.domain.repositories.state_machine.State
import com.diplomski.mucnjak.coco.domain.use_case.confirm_next_step.ConfirmNextStep
import com.diplomski.mucnjak.coco.domain.use_case.get_next_time.GetNextTime
import com.diplomski.mucnjak.coco.domain.use_case.rotate_student_screen.RotateStudentScreen
import com.diplomski.mucnjak.coco.domain.use_case.subscribe_to_navigation_state.SubscribeToNavigationState
import com.diplomski.mucnjak.coco.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class IncorrectSolutionViewModel @Inject constructor(
    private val rotateStudentScreen: RotateStudentScreen,
    private val confirmNextStep: ConfirmNextStep,
    getNextTime: GetNextTime,
    subscribeToNavigationState: SubscribeToNavigationState,
) : BaseViewModel<IncorrectSolutionState, IncorrectSolutionNavigationEvent>(IncorrectSolutionState.Initial) {

    init {
        viewModelScope.launch {
            launch {
                subscribeToNavigationState().collect {
                    if (it == State.DISCUSSION) {
                        setNavigationEvent(event = IncorrectSolutionNavigationEvent.NavigateToDiscussion)
                    }
                }
            }

            val nextTime = getNextTime()

            updateState {
                IncorrectSolutionState.Note(timeToDiscuss = nextTime.toString())
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

}