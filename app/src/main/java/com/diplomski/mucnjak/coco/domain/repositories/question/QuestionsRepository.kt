package com.diplomski.mucnjak.coco.domain.repositories.question

import com.diplomski.mucnjak.coco.data.ui.Question
import com.diplomski.mucnjak.coco.data.ui.Student

interface QuestionsRepository {

    suspend fun getAvailableQuestion(student: Student): Question

    suspend fun releaseQuestion(student: Student)

    fun clearRepository()
}