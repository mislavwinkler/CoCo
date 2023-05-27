package com.diplomski.mucnjak.coco.ui.split_screen.studentinput

import androidx.lifecycle.viewModelScope
import com.diplomski.mucnjak.coco.data.ui.Student
import com.diplomski.mucnjak.coco.domain.repositories.state_machine.State
import com.diplomski.mucnjak.coco.domain.use_case.confirm_next_step.ConfirmNextStep
import com.diplomski.mucnjak.coco.domain.use_case.reset_state_machine.ResetStateMachine
import com.diplomski.mucnjak.coco.domain.use_case.store_student.StoreStudent
import com.diplomski.mucnjak.coco.domain.use_case.subscribe_to_navigation_state.SubscribeToNavigationState
import com.diplomski.mucnjak.coco.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StudentInputViewModel @Inject constructor(
    private val storeStudent: StoreStudent,
    private val confirmNextStep: ConfirmNextStep,
    subscribeToNavigationState: SubscribeToNavigationState,
) : BaseViewModel<StudentInputState, StudentInputNavigationEvent>(StudentInputState.Input) {

    var student: Student = Student("NoStudent", position = 0, rotation = 0)

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
        student = Student(name, index, rotation)
        storeStudent.invoke(student, index)
        viewModelScope.launch {
            confirmNextStep(index)
        }
    }
}