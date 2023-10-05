package com.diplomski.mucnjak.coco.domain.interactor.consume_active_activity_config

import com.diplomski.mucnjak.coco.data.domain.ActiveActivityDomainModel
import com.diplomski.mucnjak.coco.data.remote.FirestorePaths
import com.diplomski.mucnjak.coco.domain.repositories.uuid.UuidRepository
import com.diplomski.mucnjak.coco.shared.dispatcher.Dispatcher
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ConsumeActiveActivityConfigInteractorImpl @Inject constructor(
    private val dispatcher: Dispatcher,
    private val firebaseFirestore: FirebaseFirestore,
    private val uuidRepository: UuidRepository,
) : ConsumeActiveActivityConfigInteractor {

    override suspend fun consumeActiveActivityConfig(
        activeActivityDomainModel: ActiveActivityDomainModel
    ): ActiveActivityDomainModel = withContext(dispatcher.io) {
        val uuid = uuidRepository.getUuid()
        val config = activeActivityDomainModel.configToTablet.toMutableList()

        if (!activeActivityDomainModel.configToTablet.contains(uuid.toString())) {
            config[config.indexOfFirst { it.isNullOrBlank() }] = uuid.toString()

            firebaseFirestore.collection(FirestorePaths.ACTIVE_ACTIVITY_COLLECTION)
                .document(activeActivityDomainModel.id)
                .update(ActiveActivityDomainModel::configToTablet.name, config)
                .await()

            val activeNumOfStudents =
                activeActivityDomainModel.numOfStudents[config.indexOf(uuid.toString())]

            return@withContext activeActivityDomainModel.copy(
                configToTablet = config,
                activeNumOfStudents = activeNumOfStudents,
                groupIndex = config.indexOf(uuid.toString())
            )
        }

        val activeNumOfStudents =
            activeActivityDomainModel.numOfStudents[config.indexOf(uuid.toString())]

        return@withContext activeActivityDomainModel.copy(
            activeNumOfStudents = activeNumOfStudents,
            groupIndex = config.indexOf(uuid.toString())
        )
    }
}