package com.diplomski.mucnjak.coco.domain.mapper.answer

import com.diplomski.mucnjak.coco.data.domain.ActiveActivityDomainModel
import javax.inject.Inject

class AnswersMapper @Inject constructor() :
    AnswerMappers.AnswersMapper {

    override fun mapToUiModel(domainModel: ActiveActivityDomainModel) =
        domainModel.answers.keys.toList()
}