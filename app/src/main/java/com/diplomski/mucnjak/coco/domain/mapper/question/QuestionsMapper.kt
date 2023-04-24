package com.diplomski.mucnjak.coco.domain.mapper.question

import com.diplomski.mucnjak.coco.data.domain.ActiveActivityDomainModel
import com.diplomski.mucnjak.coco.data.ui.Question
import javax.inject.Inject

class QuestionsMapper @Inject constructor() : QuestionsMappers.QuestionMapper {

    override fun mapToUiModel(domainModel: ActiveActivityDomainModel) =
        domainModel.answers
            .asSequence()
            .groupBy { domainModel.questions[it.value] }
            .mapValues { mapEntry ->
                mapEntry.value.map { answer -> answer.key }
            }
            .map {
                Question(
                    questionText = it.key,
                    answers = it.value
                )
            }
}
