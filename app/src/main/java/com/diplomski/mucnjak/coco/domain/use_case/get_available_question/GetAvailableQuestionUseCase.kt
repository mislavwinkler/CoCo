package com.diplomski.mucnjak.coco.domain.use_case.get_available_question

import com.diplomski.mucnjak.coco.data.ui.Question
import com.diplomski.mucnjak.coco.domain.repositories.question.QuestionsRepository
import javax.inject.Inject

class GetAvailableQuestionUseCase @Inject constructor(
    private val questionsRepository: QuestionsRepository
) : GetAvailableQuestion {

    override suspend fun invoke(studentIndex: Int): Question =
        questionsRepository.getAvailableQuestion(studentIndex)
}