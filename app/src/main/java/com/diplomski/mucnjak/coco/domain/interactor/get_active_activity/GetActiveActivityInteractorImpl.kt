package com.diplomski.mucnjak.coco.domain.interactor.get_active_activity

import com.diplomski.mucnjak.coco.data.remote.FirestorePaths
import com.diplomski.mucnjak.coco.data.remote.exceptions.NoDocumentException
import com.diplomski.mucnjak.coco.data.remote.response.ActiveActivityResponse
import com.diplomski.mucnjak.coco.domain.mapper.active_activity.ActiveActivityMappers
import com.diplomski.mucnjak.coco.shared.dispatcher.Dispatcher
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.cancel
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.channels.trySendBlocking
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetActiveActivityInteractorImpl @Inject constructor(
    private val dispatcher: Dispatcher,
    private val firestore: FirebaseFirestore,
    private val mapper: ActiveActivityMappers.ActiveActivityDomainMapper
) : GetActiveActivityInteractor {

    override suspend fun getActiveActivity() = withContext(dispatcher.io) {
        callbackFlow {
            firestore.collection(FirestorePaths.ACTIVE_ACTIVITY_COLLECTION)
                .limit(1)
                .get()
                .addOnCompleteListener {
                    if (it.isSuccessful) {
                        val document = it.result.documents.first()
                        val activeActivityResponse = document.toObject(ActiveActivityResponse::class.java)

                        if (activeActivityResponse == null) {
                            close(NoDocumentException(message = "No document at ${FirestorePaths.ACTIVE_ACTIVITY_COLLECTION}"))
                            return@addOnCompleteListener
                        }

                        val newActiveActivity =
                            mapper.mapToDomainModel(Pair(document.id, activeActivityResponse))
                        trySendBlocking(newActiveActivity)
                    } else {
                        close(
                            NoDocumentException(
                                message = "Failed to fetch document at ${FirestorePaths.ACTIVE_ACTIVITY_COLLECTION}",
                                it.exception
                            )
                        )
                    }
                }
                .await()

            awaitClose { }
        }.first()
    }
}
