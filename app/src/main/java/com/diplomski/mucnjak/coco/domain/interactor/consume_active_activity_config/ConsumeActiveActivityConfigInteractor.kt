package com.diplomski.mucnjak.coco.domain.interactor.consume_active_activity_config

import com.diplomski.mucnjak.coco.data.domain.ActiveActivityDomainModel

interface ConsumeActiveActivityConfigInteractor {

    suspend fun consumeActiveActivityConfig(activeActivityDomainModel: ActiveActivityDomainModel): ActiveActivityDomainModel
}