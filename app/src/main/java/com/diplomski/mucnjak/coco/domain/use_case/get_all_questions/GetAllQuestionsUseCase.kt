package com.diplomski.mucnjak.coco.domain.use_case.get_all_questions

import com.diplomski.mucnjak.coco.domain.mapper.question.QuestionsMappers
import com.diplomski.mucnjak.coco.domain.repositories.active_activity.ActiveActivityRepository
import javax.inject.Inject

class GetAllQuestionsUseCase @Inject constructor(
    private val activeActivityRepository: ActiveActivityRepository,
    private val questionMapper: QuestionsMappers.QuestionMapper
) : GetAllQuestions {

    override suspend fun invoke() =
        questionMapper.mapToUiModel(activeActivityRepository.getActiveActivity())
}