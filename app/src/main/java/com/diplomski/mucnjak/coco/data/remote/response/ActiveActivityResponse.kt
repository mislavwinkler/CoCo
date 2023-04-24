package com.diplomski.mucnjak.coco.data.remote.response

import com.diplomski.mucnjak.coco.extensions.empty

data class ActiveActivityResponse(
    val answers: Map<String, Int> = emptyMap(),
    val configToTablet: List<String?> = emptyList(),
    val numOfStudents: List<Int> = emptyList(),
    val questions: List<String> = emptyList(),
    val subTopic: String = String.empty,
    val times: Map<String, Int> = emptyMap(),
    val topic: String = String.empty,
)
