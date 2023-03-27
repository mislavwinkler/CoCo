package com.diplomski.mucnjak.coco.domain.mapper

interface UiMapper<NetworkModel, UiModel> {
    fun mapToUiModel(networkModel: NetworkModel): UiModel
}

interface NetworkMapper<NetworkModel, UiModel> {
    fun mapToNetworkModel(uiModel: UiModel): NetworkModel
}
