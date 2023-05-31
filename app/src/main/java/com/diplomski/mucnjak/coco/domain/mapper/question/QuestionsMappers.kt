package com.diplomski.mucnjak.coco.domain.mapper.question

import com.diplomski.mucnjak.coco.data.domain.ActiveActivityDomainModel
import com.diplomski.mucnjak.coco.data.ui.Question
import com.diplomski.mucnjak.coco.domain.mapper.UiMapper

interface QuestionsMappers {

    interface QuestionMapper : UiMapper<ActiveActivityDomainModel, List<Question>>
}
