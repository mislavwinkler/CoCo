package com.diplomski.mucnjak.coco.ui.split_screen

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.diplomski.mucnjak.coco.domain.use_case.get_num_of_students.GetNumOfStudents
import com.diplomski.mucnjak.coco.domain.use_case.get_student_rotation.SubscribeToStudentRotation
import com.diplomski.mucnjak.coco.domain.use_case.reset_state_machine.ResetStateMachine
import com.diplomski.mucnjak.coco.shared.NoNavigationEvent
import com.diplomski.mucnjak.coco.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplitViewModel @Inject constructor(
    val getNumOfStudents: GetNumOfStudents,
    val subscribeToStudentRotation: SubscribeToStudentRotation,
    resetStateMachine: ResetStateMachine,
) : BaseViewModel<SplitState, NoNavigationEvent>(SplitState.Initial(0, emptyList())) {

    init {
        viewModelScope.launch {
            resetStateMachine()

            val numOfStudents = getNumOfStudents()

            updateState { SplitState.Initial(numOfStudents, listOf(0, 0, 0, 0)) }

            for (i in 0 until numOfStudents) {
                launch {
                    subscribeToStudentRotation(i).collect { newRotation ->
                        Log.d("JakovLogs", "JakovLogs $newRotation rotation")
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