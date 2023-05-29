package com.diplomski.mucnjak.coco.domain.use_case.add_answer

import com.diplomski.mucnjak.coco.data.ui.Answer
import com.diplomski.mucnjak.coco.domain.repositories.students.StudentRepository
import javax.inject.Inject

class AddAnswerUseCase @Inject constructor(
    private val studentRepository: StudentRepository
): AddAnswer {

    override fun invoke(studentIndex: Int, answer: Answer) = studentRepository.addAnswer(
        studentIndex = studentIndex,
        answer = answer
    )
}