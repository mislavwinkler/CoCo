package com.diplomski.mucnjak.coco.domain.mapper.active_activity

import com.diplomski.mucnjak.coco.data.domain.ActiveActivityDomainModel
import com.diplomski.mucnjak.coco.data.domain.Id
import com.diplomski.mucnjak.coco.data.remote.response.ActiveActivityResponse
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
            configToTablet = activeActivityResponse.configToTablet,
            numOfStudents = activeActivityResponse.numOfStudents,
            questions = activeActivityResponse.questions,
            subTopic = activeActivityResponse.subTopic,
            times = activeActivityResponse.times,
            topic = activeActivityResponse.topic,
        )
    }
}