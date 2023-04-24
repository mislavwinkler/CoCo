package com.diplomski.mucnjak.coco.domain.storages.base

interface Storage<Data> {

    fun storeData(data: Data)

    fun getData(): Data

    fun clearData()
}