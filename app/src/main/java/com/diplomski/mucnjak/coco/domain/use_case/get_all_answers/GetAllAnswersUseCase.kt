package com.diplomski.mucnjak.coco.domain.use_case.get_all_answers

import com.diplomski.mucnjak.coco.data.domain.AnswersDomainModel
import com.diplomski.mucnjak.coco.data.ui.Answer
import com.diplomski.mucnjak.coco.domain.mapper.answer.AnswerMappers
import com.diplomski.mucnjak.coco.domain.repositories.active_activity.ActiveActivityRepository
import javax.inject.Inject

class GetAllAnswersUseCase @Inject constructor(
    private val activeActivityRepository: ActiveActivityRepository,
    private val answersMapper: AnswerMappers.AnswersMapper
) : GetAllAnswers {

    override suspend fun invoke(): List<Answer> =
        activeActivityRepository.getActiveActivity().let { (_, answers, answerType) ->
            answersMapper.mapToUiModel(
                AnswersDomainModel(
                    answers = answers.keys.toList(),
                    answerType = answerType
                )
            )
        }
}
