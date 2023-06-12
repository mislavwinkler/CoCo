package com.diplomski.mucnjak.coco.data.remote.request

data class AnalyticsRequest(
    val date: com.google.firebase.Timestamp,
    val group: Int,
    val subtopic: String,
    val topic: String,
    val results: List<Map<String, Any>>,
    val discussionTimes: List<Int>,
)
