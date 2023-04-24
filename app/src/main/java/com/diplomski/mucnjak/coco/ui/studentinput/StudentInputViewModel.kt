package com.diplomski.mucnjak.coco.ui.studentinput

import androidx.lifecycle.viewModelScope
import com.diplomski.mucnjak.coco.data.ui.Student
import com.diplomski.mucnjak.coco.domain.storages.base.Storage
import com.diplomski.mucnjak.coco.domain.use_case.get_num_of_students.GetNumOfStudents
import com.diplomski.mucnjak.coco.extensions.empty
import com.diplomski.mucnjak.coco.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StudentInputViewModel @Inject constructor(
    private val getNumOfStudents: GetNumOfStudents
) : BaseViewModel<StudentInputState>(StudentInputState.Initial) {

    private val students: MutableList<Student> = mutableListOf()

    init {
        viewModelScope.launch {
            val numOfStudents = getNumOfStudents()

            repeat(numOfStudents) { index ->
                students.add(
                    Student(name = String.empty, index)
                )
            }

            updateState {
                StudentInputState.Input(
                    numOfStudents = students.count(),
                    students = students
                )
            }
        }
    }

    fun confirmStudent(name: String, index: Int) {
        students[students.indexOfFirst { it.position == index }] = Student(name, index)
    }
}