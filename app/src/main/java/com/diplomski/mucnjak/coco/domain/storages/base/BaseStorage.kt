package com.diplomski.mucnjak.coco.domain.storages.base

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow

abstract class BaseListStorage<Data> : Storage<Data> {

    private var data: MutableList<Data> = mutableListOf()

    private val dataUpdatesMutableFlow: MutableSharedFlow<StorageUpdate<Data>> = MutableSharedFlow()

    override val dataUpdatesFlow: Flow<StorageUpdate<Data>> = dataUpdatesMutableFlow

    override fun addData(data: Data) {
        this.data.add(data)

        dataUpdatesMutableFlow.tryEmit(
            StorageUpdate(
                updateType = StorageUpdateType.INSERT,
                oldData = data
            )
        )
    }

    @Throws(IllegalStateException::class)
    override fun getAllData(): List<Data> = data

    override fun getData(index: Int): Data = data[index]

    override fun updateData(index: Int, data: Data) {
        val oldData = this.data[index]
        this.data[index] = data

        dataUpdatesMutableFlow.tryEmit(
            StorageUpdate(
                updateType = StorageUpdateType.UPDATE,
                oldData = oldData,
                newData = data
            )
        )
    }

    override fun clearData() {
        data.clear()
        dataUpdatesMutableFlow.tryEmit(
            StorageUpdate(
                updateType = StorageUpdateType.DELETE_ALL,
            )
        )
    }
}