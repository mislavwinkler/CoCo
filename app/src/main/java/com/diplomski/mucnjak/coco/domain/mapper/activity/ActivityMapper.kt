package com.diplomski.mucnjak.coco.domain.mapper.activity

import com.diplomski.mucnjak.coco.data.domain.ActiveActivityDomainModel
import com.diplomski.mucnjak.coco.data.ui.Activity
import javax.inject.Inject

class ActivityMapper @Inject constructor(): ActivityMappers.ActivityMapper {

    override fun mapToUiModel(domainModel: ActiveActivityDomainModel) = Activity(
        topic = domainModel.topic,
        subTopic = domainModel.subTopic,
        numOfStudent = domainModel.activeNumOfStudents
    )
}