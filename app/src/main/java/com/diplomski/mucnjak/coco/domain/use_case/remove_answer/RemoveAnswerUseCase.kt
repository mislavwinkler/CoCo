package com.diplomski.mucnjak.coco.domain.use_case.remove_answer

import com.diplomski.mucnjak.coco.data.ui.Answer
import com.diplomski.mucnjak.coco.domain.repositories.students.StudentRepository
import javax.inject.Inject

class RemoveAnswerUseCase @Inject constructor(
    private val studentRepository: StudentRepository,
) : RemoveAnswer {

    override fun invoke(studentIndex: Int, answer: Answer): Unit = studentRepository.removeAnswer(
        studentIndex = studentIndex,
        answer = answer
    )
}