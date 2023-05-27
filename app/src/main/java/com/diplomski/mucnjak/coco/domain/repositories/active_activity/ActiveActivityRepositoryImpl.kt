package com.diplomski.mucnjak.coco.domain.repositories.active_activity

import com.diplomski.mucnjak.coco.data.domain.ActiveActivityDomainModel
import com.diplomski.mucnjak.coco.domain.interactor.consume_active_activity_config.ConsumeActiveActivityConfigInteractor
import com.diplomski.mucnjak.coco.domain.interactor.get_active_activity.GetActiveActivityInteractor
import com.diplomski.mucnjak.coco.domain.repositories.uuid.UuidRepository
import com.diplomski.mucnjak.coco.shared.dispatcher.Dispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ActiveActivityRepositoryImpl @Inject constructor(
    private val getActiveActivityInteractor: GetActiveActivityInteractor,
    private val consumeActiveActivityConfigInteractor: ConsumeActiveActivityConfigInteractor,
) : ActiveActivityRepository {

    private var activeActivity: ActiveActivityDomainModel? = null

    override suspend fun getActiveActivity() = activeActivity
        ?: getActiveActivityInteractor.getActiveActivity().let {
            activeActivity = consumeActiveActivityConfigInteractor.consumeActiveActivityConfig(it)
            return@let activeActivity ?: throw IllegalStateException()
        }

    override fun getLocalActiveActivity() = activeActivity

    override fun clearRepository() {
        activeActivity = null
    }
}