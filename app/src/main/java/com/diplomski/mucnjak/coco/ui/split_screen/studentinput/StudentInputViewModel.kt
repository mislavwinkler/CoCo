package com.diplomski.mucnjak.coco.ui.split_screen.studentinput

import androidx.lifecycle.viewModelScope
import com.diplomski.mucnjak.coco.analytics.Analytics
import com.diplomski.mucnjak.coco.data.ui.Student
import com.diplomski.mucnjak.coco.domain.repositories.state_machine.State
import com.diplomski.mucnjak.coco.domain.use_case.confirm_next_step.ConfirmNextStep
import com.diplomski.mucnjak.coco.domain.use_case.revoke_next_step_confirmation.RevokeNextStepConfirmation
import com.diplomski.mucnjak.coco.domain.use_case.store_student.StoreStudent
import com.diplomski.mucnjak.coco.domain.use_case.subscribe_to_navigation_state.SubscribeToNavigationState
import com.diplomski.mucnjak.coco.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.io.PipedReader
import javax.inject.Inject

@HiltViewModel
class StudentInputViewModel @Inject constructor(
    private val storeStudent: StoreStudent,
    private val confirmNextStep: ConfirmNextStep,
    private val revokeNextStepConfirmation: RevokeNextStepConfirmation,
    private val analytics: Analytics,
    subscribeToNavigationState: SubscribeToNavigationState,
) : BaseViewModel<StudentInputState, StudentInputNavigationEvent>(StudentInputState.Input()) {

    var student: Student = Student("NoStudent", position = 0, rotation = 0)
    var isConfirmed: Boolean = false

    init {
        viewModelScope.launch {
            launch {
                subscribeToNavigationState().collect {
                    if (it == State.SETUP) {
                        setNavigationEvent(StudentInputNavigationEvent.NavigateToSetup)
                    }
                }
            }
        }
    }

    fun confirmStudent(name: String, index: Int, rotation: Int = 0) {
        if (name.isBlank()) {
            return
        }
        if (isConfirmed) {
            isConfirmed = false
            unconfirmStudent(index)
            updateState { state ->
                (state as? StudentInputState.Input)?.copy(isConfirmed = false) ?: state
            }
        } else {
            isConfirmed = true
            analytics.sendStudentReady(index, "Student input")
            student = Student(name, index, rotation)
            viewModelScope.launch {
                storeStudent(student, index)
                confirmNextStep(index)
            }
            updateState { state ->
                (state as? StudentInputState.Input)?.copy(isConfirmed = true) ?: state
            }
        }
    }

    private fun unconfirmStudent(index: Int) {
        viewModelScope.launch {
            revokeNextStepConfirmation(index)
        }
    }
}