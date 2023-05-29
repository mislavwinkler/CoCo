package com.diplomski.mucnjak.coco.domain.mapper.answer

import com.diplomski.mucnjak.coco.data.domain.AnswersDomainModel
import com.diplomski.mucnjak.coco.data.ui.Answer
import com.diplomski.mucnjak.coco.domain.mapper.UiMapper

interface AnswerMappers {

    interface AnswersMapper : UiMapper<AnswersDomainModel, List<Answer>>
}