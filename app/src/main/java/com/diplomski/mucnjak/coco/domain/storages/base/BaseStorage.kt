package com.diplomski.mucnjak.coco.domain.storages.base

import kotlin.jvm.Throws

abstract class BaseStorage<Data> : Storage<Data> {

    private var data: Data? = null

    override fun storeData(data: Data) {
        this.data = data
    }

    @Throws(IllegalStateException::class)
    override fun getData(): Data = data ?: throw IllegalStateException("Storage empty")

    override fun clearData() {
        data = null
    }
}