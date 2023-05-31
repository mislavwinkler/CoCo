package com.diplomski.mucnjak.coco.domain.interactor.get_active_activity

import com.diplomski.mucnjak.coco.data.domain.ActiveActivityDomainModel
import com.diplomski.mucnjak.coco.data.remote.FirestorePaths
import com.diplomski.mucnjak.coco.data.remote.exceptions.NoDocumentException
import com.diplomski.mucnjak.coco.data.remote.response.ActiveActivityResponse
import com.diplomski.mucnjak.coco.data.ui.AnswerType
import com.diplomski.mucnjak.coco.domain.mapper.active_activity.ActiveActivityMappers
import com.diplomski.mucnjak.coco.shared.dispatcher.Dispatcher
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.channels.trySendBlocking
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetActiveActivityInteractorImpl @Inject constructor(
    private val dispatcher: Dispatcher,
    private val firebaseFirestore: FirebaseFirestore,
    private val firebaseStorage: FirebaseStorage,
    private val mapper: ActiveActivityMappers.ActiveActivityDomainMapper
) : GetActiveActivityInteractor {

    override suspend fun getActiveActivity(): ActiveActivityDomainModel =
        withContext(dispatcher.io) {
            val activeActivity = callbackFlow {
                firebaseFirestore.collection(FirestorePaths.ACTIVE_ACTIVITY_COLLECTION)
                    .limit(1)
                    .get()
                    .addOnCompleteListener {
                        if (it.isSuccessful && it.result.documents.isNotEmpty()) {
                            val document = it.result.documents.first()
                            val activeActivityResponse =
                                document.toObject(ActiveActivityResponse::class.java)

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

            if (activeActivity.answerType == AnswerType.IMAGE) {
                val mapOfAnswerUrls = mutableMapOf<String, Int>()
                coroutineScope {
                    activeActivity.answers.forEach {
                        launch {
                            mapOfAnswerUrls[firebaseStorage.getReferenceFromUrl(it.key).downloadUrl.await()
                                .toString()] = it.value
                        }
                    }
                }
                return@withContext activeActivity.copy(answers = mapOfAnswerUrls)
            }

            return@withContext activeActivity
        }
}
