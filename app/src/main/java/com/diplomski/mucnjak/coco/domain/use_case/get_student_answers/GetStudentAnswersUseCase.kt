package com.diplomski.mucnjak.coco.domain.use_case.get_student_answers

import com.diplomski.mucnjak.coco.data.ui.Answer
import com.diplomski.mucnjak.coco.domain.repositories.students.StudentRepository
import javax.inject.Inject

class GetStudentAnswersUseCase @Inject constructor(
    private val studentRepository: StudentRepository
) : GetStudentAnswers {

    override fun invoke(studentIndex: Int): List<Answer> = studentRepository.getStudentAnswers(studentIndex)
}