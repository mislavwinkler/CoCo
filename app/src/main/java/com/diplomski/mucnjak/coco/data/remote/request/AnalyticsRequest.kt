package com.diplomski.mucnjak.coco.data.remote.request

data class AnalyticsRequest(
    val activityId: String,
    val date: com.google.firebase.Timestamp,
    val group: Int,
    val subtopic: String,
    val topic: String,
    val results: List<Map<String, Any>>,
    val numberOfPossibleAnswers: Int,
    val discussionTimes: List<Int>,
    val discussionTimesMax: List<Int>,
    val resolutionTimesMax: List<Int>
)
