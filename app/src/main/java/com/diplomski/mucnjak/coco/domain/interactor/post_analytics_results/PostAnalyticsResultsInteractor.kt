package com.diplomski.mucnjak.coco.domain.interactor.post_analytics_results

import com.diplomski.mucnjak.coco.data.remote.request.AnalyticsRequest
import com.google.firebase.firestore.DocumentReference

interface PostAnalyticsResultsInteractor {

    suspend fun addAnalyticsResults(resultsRequest: AnalyticsRequest): DocumentReference

    suspend fun updateAnalyticsResults(analyticsDocument: DocumentReference, resultsRequest: AnalyticsRequest)
}