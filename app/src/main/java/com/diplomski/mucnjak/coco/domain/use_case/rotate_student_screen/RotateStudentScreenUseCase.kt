package com.diplomski.mucnjak.coco.domain.use_case.rotate_student_screen

import com.diplomski.mucnjak.coco.domain.repositories.students.StudentRepository
import javax.inject.Inject

const val DEFAULT_ROTATION_STEP: Int = 90

class RotateStudentScreenUseCase @Inject constructor(
    private val studentRepository: StudentRepository
) : RotateStudentScreen {

    override suspend fun invoke(studentIndex: Int) {
        val student = studentRepository.getStudent(studentIndex)
            ?: throw NullPointerException("Missing student for rotating action")
        studentRepository.updateStudent(
            studentIndex,
            student.copy(rotation = student.rotation + DEFAULT_ROTATION_STEP)
        )
    }
}