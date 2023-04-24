package com.diplomski.mucnjak.coco.domain.use_case.get_available_question

import com.diplomski.mucnjak.coco.data.ui.Student
import com.diplomski.mucnjak.coco.domain.repositories.question.QuestionsRepository
import javax.inject.Inject

class GetAvailableQuestionUseCase @Inject constructor(
    private val questionsRepository: QuestionsRepository
) : GetAvailableQuestion {

    override suspend fun invoke(student: Student) =
        questionsRepository.getAvailableQuestion(student)
}