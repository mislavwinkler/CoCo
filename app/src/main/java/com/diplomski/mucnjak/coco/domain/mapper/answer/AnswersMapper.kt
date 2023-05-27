package com.diplomski.mucnjak.coco.domain.mapper.answer

import com.diplomski.mucnjak.coco.data.domain.ActiveActivityDomainModel
import com.diplomski.mucnjak.coco.data.ui.Answer
import com.diplomski.mucnjak.coco.data.ui.AnswerType
import javax.inject.Inject

class AnswersMapper @Inject constructor() : AnswerMappers.AnswersMapper {

    override fun mapToUiModel(domainModel: ActiveActivityDomainModel) =
        domainModel.answers.keys.map {
            if (domainModel.answerType == AnswerType.IMAGE) {
                Answer(
                    domainModel.answerImages[it].orEmpty(),
                    domainModel.answerType
                )
            } else {
                Answer(it, domainModel.answerType)
            }
        }.toList()
}