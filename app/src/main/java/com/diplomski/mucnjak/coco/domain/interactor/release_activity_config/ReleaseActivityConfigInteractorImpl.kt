package com.diplomski.mucnjak.coco.domain.interactor.release_activity_config

import com.diplomski.mucnjak.coco.data.remote.FirestorePaths
import com.diplomski.mucnjak.coco.data.remote.exceptions.NoDocumentException
import com.diplomski.mucnjak.coco.data.remote.response.ActiveActivityResponse
import com.diplomski.mucnjak.coco.domain.repositories.active_activity.ActiveActivityRepository
import com.diplomski.mucnjak.coco.domain.repositories.uuid.UuidRepository
import com.diplomski.mucnjak.coco.shared.dispatcher.Dispatcher
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ReleaseActivityConfigInteractorImpl @Inject constructor(
    private val dispatcher: Dispatcher,
    private val firestore: FirebaseFirestore,
    private val activeActivityRepository: ActiveActivityRepository,
    private val uuidRepository: UuidRepository,
) : ReleaseActivityConfigInteractor {

    override suspend fun releaseActivityConfig() = withContext(dispatcher.io) {
        val activityId = activeActivityRepository.getActiveActivity().id
        val activity = firestore.collection(FirestorePaths.ACTIVE_ACTIVITY_COLLECTION)
            .document(activityId)
            .get()
            .await()
            .toObject(ActiveActivityResponse::class.java) ?: throw NoDocumentException("Can't release activity config from ${FirestorePaths.ACTIVE_ACTIVITY_COLLECTION} because document can't be found")

        val updatedConfig = activity.configToTablet.toMutableList<String?>()
        updatedConfig[updatedConfig.indexOf(uuidRepository.getUuid().toString())] = null

        firestore.collection(FirestorePaths.ACTIVE_ACTIVITY_COLLECTION)
            .document(activityId)
            .update(ActiveActivityResponse::configToTablet.name, updatedConfig)
            .await()

        return@withContext
    }
}