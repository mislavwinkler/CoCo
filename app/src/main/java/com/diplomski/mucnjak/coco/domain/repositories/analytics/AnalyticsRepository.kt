package com.diplomski.mucnjak.coco.domain.repositories.analytics

interface AnalyticsRepository {
    fun init()
    fun calculateAndStoreAccuracies()
    fun storeDiscussionTime()
    fun storeResolutionChangeTime(studentIndex: Int)
    fun storeResolutionTimeout()
    suspend fun postAnalytics()
}