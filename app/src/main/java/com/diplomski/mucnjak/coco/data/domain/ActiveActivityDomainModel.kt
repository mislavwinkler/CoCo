package com.diplomski.mucnjak.coco.data.domain

data class ActiveActivityDomainModel(
    val id: Id,
    val answers: Map<String, Int>,
    val configToTablet: List<String?>,
    val numOfStudents: List<Int>,
    val questions: List<String>,
    val subTopic: String,
    val times: Map<String, Int>,
    val topic: String,
    val activeNumOfStudents: Int = 0,
)
