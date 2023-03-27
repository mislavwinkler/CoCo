package com.diplomski.mucnjak.coco.ui.studentinput

import com.diplomski.mucnjak.coco.data.ui.Student
import com.diplomski.mucnjak.coco.domain.storages.Storage
import com.diplomski.mucnjak.coco.extensions.empty
import com.diplomski.mucnjak.coco.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class StudentInputViewModel @Inject constructor(
    private val studentStorage: Storage<Student>
) : BaseViewModel<StudentInputState>(StudentInputState.Initial) {

    private val students: MutableList<Student> = mutableListOf(
        Student(String.empty, 0),
        Student(String.empty, 1),
        Student(String.empty, 2),
        Student(String.empty, 3),
    )

    init {
        updateState {
            StudentInputState.Input(
                numOfStudents = students.count(),
                students = students
            )
        }
    }

    fun confirmStudent(name: String, index: Int) {
        studentStorage.storeData(data = Student(name = name, position = index))
    }
}