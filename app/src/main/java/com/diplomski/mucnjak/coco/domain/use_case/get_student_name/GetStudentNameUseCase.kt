package com.diplomski.mucnjak.coco.domain.use_case.get_student_name

import com.diplomski.mucnjak.coco.domain.repositories.students.StudentRepository
import com.diplomski.mucnjak.coco.extensions.empty
import javax.inject.Inject

class GetStudentNameUseCase @Inject constructor(
    private val studentRepository: StudentRepository,
) : GetStudentName {

    override fun invoke(studentIndex: Int): String =
        studentRepository.getStudent(studentIndex)?.name ?: String.empty
}