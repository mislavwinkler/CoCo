package com.diplomski.mucnjak.coco.data.remote.response

import com.diplomski.mucnjak.coco.extensions.empty

data class ActiveActivityResponse(
    val answers: Map<String, Int> = emptyMap(),
    // Typo in DB
    @Suppress("SpellCheckingInspection") val anwserTypeImage: Boolean = false,
    val configToTablet: List<String?> = emptyList(),
    val correctionTimes: List<Int> = emptyList(),
    val discussionTimes: List<Int> = emptyList(),
    val numOfStudents: List<Int> = emptyList(),
    val questions: List<String> = emptyList(),
    val solvingTime: Int = 0,
    val subTopic: String = String.empty,
    val topic: String = String.empty,
)
