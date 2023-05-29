package com.diplomski.mucnjak.coco.domain.mapper.answer

import com.diplomski.mucnjak.coco.data.domain.AnswersDomainModel
import com.diplomski.mucnjak.coco.data.ui.Answer
import javax.inject.Inject

class AnswersMapper @Inject constructor() : AnswerMappers.AnswersMapper {

    override fun mapToUiModel(domainModel: AnswersDomainModel) =
        domainModel.answers.map { Answer(it, domainModel.answerType) }.toList()
}