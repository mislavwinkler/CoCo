package com.diplomski.mucnjak.coco.domain.mapper.lesson_mapper

import com.diplomski.mucnjak.coco.data.remote.response.LessonResponse
import com.diplomski.mucnjak.coco.data.ui.Lesson
import com.diplomski.mucnjak.coco.domain.mapper.UiMapper

interface LessonMappers {

    interface LessonMapper : UiMapper<LessonResponse, Lesson>
}