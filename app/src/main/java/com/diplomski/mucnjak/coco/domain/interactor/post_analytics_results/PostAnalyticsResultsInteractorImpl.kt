package com.diplomski.mucnjak.coco.domain.interactor.post_analytics_results

import com.diplomski.mucnjak.coco.data.remote.FirestorePaths
import com.diplomski.mucnjak.coco.data.remote.request.AnalyticsRequest
import com.diplomski.mucnjak.coco.shared.dispatcher.Dispatcher
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import kotlinx.coroutines.withTimeout
import javax.inject.Inject

class PostAnalyticsResultsInteractorImpl @Inject constructor(
    val dispatcher: Dispatcher,
    private val firebaseFirestore: FirebaseFirestore,
) : PostAnalyticsResultsInteractor {

    override suspend fun postAnalyticsResults(resultsRequest: AnalyticsRequest) {
        firebaseFirestore.collection(FirestorePaths.ANALYTICS_COLLECTION).add(
            resultsRequest
        ).await()
    }
}
