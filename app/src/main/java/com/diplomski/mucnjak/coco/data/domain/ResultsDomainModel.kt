package com.diplomski.mucnjak.coco.data.domain

data class ResultsDomainModel(
    val date: com.google.firebase.Timestamp,
    val group: Int,
    val subtopic: String,
    val topic: String,
    val discussionTimes: MutableList<Int>,
    val results: MutableList<StudentResultsDomainModel>,
)
