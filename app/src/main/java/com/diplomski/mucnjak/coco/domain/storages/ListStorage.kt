package com.diplomski.mucnjak.coco.domain.storages

abstract class ListStorage<Data> : Storage<Data> {

    private val data: MutableList<Data> = mutableListOf()

    override fun storeData(data: Data) {
        this.data.add(data)
    }

    override fun getLatestData() = data.last()

    override fun getAllData() = data.toList()

    override fun clearData() = data.clear()
}
