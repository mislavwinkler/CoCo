package com.diplomski.mucnjak.coco.domain.use_case.get_lessons

import com.diplomski.mucnjak.coco.data.ui.Lesson

interface GetLessons {
    suspend operator fun invoke(): List<Lesson>
}