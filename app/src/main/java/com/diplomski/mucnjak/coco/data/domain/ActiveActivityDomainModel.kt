package com.diplomski.mucnjak.coco.data.domain

import com.diplomski.mucnjak.coco.data.ui.AnswerType

data class ActiveActivityDomainModel(
    val id: Id,
    val answers: Map<String, Int>,
    val answerImages: Map<String, String> = emptyMap(),
    val answerType: AnswerType,
    val configToTablet: List<String?>,
    val numOfStudents: List<Int>,
    val questions: List<String>,
    val subTopic: String,
    val correctionTimes: List<Int> = emptyList(),
    val discussionTimes: List<Int> = emptyList(),
    val solvingTime: Int = 0,
    val topic: String,
    val activeNumOfStudents: Int = 0,
)
