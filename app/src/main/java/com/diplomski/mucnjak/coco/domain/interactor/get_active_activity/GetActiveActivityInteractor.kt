package com.diplomski.mucnjak.coco.domain.interactor.get_active_activity

import com.diplomski.mucnjak.coco.data.domain.ActiveActivityDomainModel

interface GetActiveActivityInteractor {
    suspend fun getActiveActivity(): ActiveActivityDomainModel
}