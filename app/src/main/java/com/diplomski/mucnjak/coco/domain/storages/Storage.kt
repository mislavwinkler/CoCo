package com.diplomski.mucnjak.coco.domain.storages

interface Storage<Data> {

    fun storeData(data: Data)

    fun getLatestData(): Data

    fun getAllData(): List<Data>

    fun clearData()
}