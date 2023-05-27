package com.diplomski.mucnjak.coco.domain.use_case.rotate_student_screen

interface RotateStudentScreen {
    suspend operator fun invoke(studentIndex: Int)
}