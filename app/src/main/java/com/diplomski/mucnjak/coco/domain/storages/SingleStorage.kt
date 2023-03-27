package com.diplomski.mucnjak.coco.domain.storages

abstract class SingleStorage<Data> : Storage<Data> {

    private var data: Data? = null

    override fun storeData(data: Data) {
        this.data = data
    }

    @kotlin.jvm.Throws(IllegalStateException::class)
    override fun getLatestData(): Data = data ?: throw IllegalStateException("Storage empty")

    @kotlin.jvm.Throws(IllegalStateException::class)
    override fun getAllData() = listOf(data ?: throw IllegalStateException("Storage empty"))

    override fun clearData() {
        data = null
    }
}