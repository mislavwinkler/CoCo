package com.diplomski.mucnjak.coco.ui.split_screen

import androidx.lifecycle.viewModelScope
import com.diplomski.mucnjak.coco.domain.repositories.state_machine.State
import com.diplomski.mucnjak.coco.domain.use_case.get_num_of_students.GetNumOfStudents
import com.diplomski.mucnjak.coco.domain.use_case.get_student_rotation.SubscribeToStudentRotation
import com.diplomski.mucnjak.coco.domain.use_case.reset_state_machine.ResetStateMachine
import com.diplomski.mucnjak.coco.domain.use_case.subscribe_to_navigation_state.SubscribeToNavigationState
import com.diplomski.mucnjak.coco.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplitViewModel @Inject constructor(
    val getNumOfStudents: GetNumOfStudents,
    val subscribeToStudentRotation: SubscribeToStudentRotation,
    val subscribeToNavigationState: SubscribeToNavigationState,
    resetStateMachine: ResetStateMachine,
) : BaseViewModel<SplitState, SplitNavigationEvent>(SplitState.Initial(0, emptyList())) {

    init {
        viewModelScope.launch {
            resetStateMachine()

            launch {
                subscribeToNavigationState().collect {
                    if (it == State.SOLUTIONS) {
                        setNavigationEvent(SplitNavigationEvent.NavigateToSolutions)
                    }
                }
            }

            val numOfStudents = getNumOfStudents()

            updateState { SplitState.Initial(numOfStudents, listOf(0, 0, 0, 0)) }

            for (i in 0 until numOfStudents) {
                launch {
                    subscribeToStudentRotation(i).collect { newRotation ->
                        updateState { state ->
                            (state as? SplitState.Initial)?.run {
                                copy(
                                    rotations = rotations.toMutableList()
                                        .apply { set(i, newRotation) })
                            } ?: state
                        }
                    }
                }
            }
        }
    }
}