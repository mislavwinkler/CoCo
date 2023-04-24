package com.diplomski.mucnjak.coco.domain.use_case.get_all_answers

import com.diplomski.mucnjak.coco.domain.mapper.answer.AnswerMappers
import com.diplomski.mucnjak.coco.domain.repositories.active_activity.ActiveActivityRepository
import javax.inject.Inject

class GetAllAnswersUseCase @Inject constructor(
    private val activeActivityRepository: ActiveActivityRepository,
    private val answersMapper: AnswerMappers.AnswersMapper
) : GetAllAnswers {

    override suspend fun invoke() =
        activeActivityRepository.getActiveActivity().let { answersMapper.mapToUiModel(it) }
}
