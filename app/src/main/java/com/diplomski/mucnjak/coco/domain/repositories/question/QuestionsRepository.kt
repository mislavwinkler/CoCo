package com.diplomski.mucnjak.coco.domain.repositories.question

import com.diplomski.mucnjak.coco.data.ui.Question

interface QuestionsRepository {

    suspend fun getAvailableQuestion(studentIndex: Int): Question

    suspend fun releaseQuestion(studentIndex: Int)

    fun getAllActiveQuestions(): List<Question>

    fun clearRepository()
}