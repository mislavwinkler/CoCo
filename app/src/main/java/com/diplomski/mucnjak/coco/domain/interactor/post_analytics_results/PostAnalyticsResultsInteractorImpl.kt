package com.diplomski.mucnjak.coco.domain.interactor.post_analytics_results

import com.diplomski.mucnjak.coco.data.remote.FirestorePaths
import com.diplomski.mucnjak.coco.data.remote.request.AnalyticsRequest
import com.diplomski.mucnjak.coco.shared.dispatcher.Dispatcher
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class PostAnalyticsResultsInteractorImpl @Inject constructor(
    val dispatcher: Dispatcher,
    private val firebaseFirestore: FirebaseFirestore,
) : PostAnalyticsResultsInteractor {

    override suspend fun addAnalyticsResults(resultsRequest: AnalyticsRequest): DocumentReference {
        return firebaseFirestore.collection(FirestorePaths.ANALYTICS_COLLECTION).add(
            resultsRequest
        ).await()
    }

    override suspend fun updateAnalyticsResults(
        analyticsDocument: DocumentReference,
        resultsRequest: AnalyticsRequest
    ) {
        analyticsDocument.set(
            resultsRequest
        ).await()
    }
}
