package com.diplomski.mucnjak.coco.domain.repositories.active_activity

import com.diplomski.mucnjak.coco.data.domain.ActiveActivityDomainModel

interface ActiveActivityRepository {
    suspend fun getActiveActivity(): ActiveActivityDomainModel

    fun getLocalActiveActivity(): ActiveActivityDomainModel?

    fun clearRepository()
}