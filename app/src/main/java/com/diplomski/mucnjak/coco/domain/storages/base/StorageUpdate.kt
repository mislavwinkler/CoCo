package com.diplomski.mucnjak.coco.domain.storages.base

data class StorageUpdate<Data>(
    val updateType: StorageUpdateType,
    val oldData: Data? = null,
    val newData: Data? = null,
)
