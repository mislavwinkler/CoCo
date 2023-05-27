package com.diplomski.mucnjak.coco.domain.mapper.active_activity

import com.diplomski.mucnjak.coco.data.domain.ActiveActivityDomainModel
import com.diplomski.mucnjak.coco.data.domain.Id
import com.diplomski.mucnjak.coco.data.remote.response.ActiveActivityResponse
import com.diplomski.mucnjak.coco.data.ui.AnswerType
import javax.inject.Inject

class ActiveActivityDomainMapper @Inject constructor() :
    ActiveActivityMappers.ActiveActivityDomainMapper {

    override fun mapToDomainModel(
        networkModel: Pair<Id, ActiveActivityResponse>
    ): ActiveActivityDomainModel {
        val activeActivityResponse = networkModel.second

        return ActiveActivityDomainModel(
            id = networkModel.first,
            answers = activeActivityResponse.answers,
            answerType = if (activeActivityResponse.anwserTypeImage) AnswerType.IMAGE else AnswerType.TEXT,
            configToTablet = activeActivityResponse.configToTablet,
            correctionTimes = activeActivityResponse.correctionTimes,
            discussionTimes = activeActivityResponse.discussionTimes,
            numOfStudents = activeActivityResponse.numOfStudents,
            questions = activeActivityResponse.questions,
            solvingTime = 1,//activeActivityResponse.solvingTime,
            subTopic = activeActivityResponse.subTopic,
            topic = activeActivityResponse.topic,
        )
    }
}