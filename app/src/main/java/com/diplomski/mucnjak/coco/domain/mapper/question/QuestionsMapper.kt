package com.diplomski.mucnjak.coco.domain.mapper.question

import com.diplomski.mucnjak.coco.data.domain.ActiveActivityDomainModel
import com.diplomski.mucnjak.coco.data.domain.AnswersDomainModel
import com.diplomski.mucnjak.coco.data.ui.Question
import com.diplomski.mucnjak.coco.domain.mapper.answer.AnswerMappers
import javax.inject.Inject

class QuestionsMapper @Inject constructor(
    private val answersMapper: AnswerMappers.AnswersMapper
) : QuestionsMappers.QuestionMapper {

    override fun mapToUiModel(domainModel: ActiveActivityDomainModel): List<Question> =
        domainModel.answers
            .asSequence()
            .groupBy { domainModel.questions[it.value] }
            .mapValues { mapEntry ->
                mapEntry.value.map { (key) -> key }
            }
            .map {
                Question(
                    questionText = it.key,
                    answers = answersMapper.mapToUiModel(
                        AnswersDomainModel(
                            answers = it.value,
                            answerType = domainModel.answerType
                        )
                    )
                )
            }
}
