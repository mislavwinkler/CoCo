package com.diplomski.mucnjak.coco.domain.repositories.question

import com.diplomski.mucnjak.coco.data.ui.Question
import com.diplomski.mucnjak.coco.data.ui.Student

interface QuestionsRepository {

    suspend fun getAvailableQuestion(studentIndex: Int): Question

    suspend fun releaseQuestion(studentIndex: Int)

    fun clearRepository()
}