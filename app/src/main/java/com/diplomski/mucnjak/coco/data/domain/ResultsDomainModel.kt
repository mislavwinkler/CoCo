package com.diplomski.mucnjak.coco.data.domain

data class ResultsDomainModel(
    val activityId: String,
    val date: com.google.firebase.Timestamp,
    val group: Int,
    val subtopic: String,
    val topic: String,
    val discussionTimes: MutableList<Int>,
    val results: MutableList<StudentResultsDomainModel>,
    val numberOfPossibleAnswers: Int,
    val discussionTimesMax: List<Int>,
    val resolutionTimesMax: List<Int>
)
