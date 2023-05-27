package com.diplomski.mucnjak.coco.domain.use_case.get_student_name

interface GetStudentName {
    operator fun invoke(studentIndex: Int): String
}