package com.diplomski.mucnjak.coco.domain.mapper.lesson_mapper

import com.diplomski.mucnjak.coco.data.remote.response.LessonResponse
import com.diplomski.mucnjak.coco.data.ui.Lesson

class LessonMapper : LessonMappers.LessonMapper {

    override fun mapToUiModel(networkModel: LessonResponse) =
        Lesson(
            topicName = networkModel.topic
        )
}