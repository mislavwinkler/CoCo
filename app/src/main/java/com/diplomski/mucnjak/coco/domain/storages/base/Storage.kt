package com.diplomski.mucnjak.coco.domain.storages.base

import kotlinx.coroutines.flow.Flow

interface Storage<Data> {

    val dataUpdatesFlow: Flow<StorageUpdate<Data>>
    fun addData(data: Data)
    fun getData(index: Int): Data
    fun getAllData(): List<Data>
    fun updateData(index: Int, data: Data)
    fun clearData()
}