package com.diplomski.mucnjak.coco.domain.interactor.post_analytics_results

import com.diplomski.mucnjak.coco.data.remote.request.AnalyticsRequest

interface PostAnalyticsResultsInteractor {

    suspend fun postAnalyticsResults(resultsRequest: AnalyticsRequest)
}