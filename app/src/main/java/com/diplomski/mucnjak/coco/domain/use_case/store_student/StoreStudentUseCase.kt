package com.diplomski.mucnjak.coco.domain.use_case.store_student

import com.diplomski.mucnjak.coco.data.ui.Student
import com.diplomski.mucnjak.coco.domain.repositories.students.StudentRepository
import javax.inject.Inject

class StoreStudentUseCase @Inject constructor(
    private val studentRepository: StudentRepository
) : StoreStudent {

    override fun invoke(student: Student, studentIndex: Int) =
        studentRepository.storeStudent(student, studentIndex)
}