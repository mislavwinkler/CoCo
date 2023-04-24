package com.diplomski.mucnjak.coco.domain.repositories.uuid

import java.util.UUID

interface UuidRepository {
    fun getUuid(): UUID
}