package com.diplomski.mucnjak.coco.ui.studentinput

import com.diplomski.mucnjak.coco.data.ui.Student

sealed class StudentInputState {
    object Initial : StudentInputState()
    data class Input(
        val numOfStudents: Int,
        val students: List<Student>,
    ) : StudentInputState()
}
