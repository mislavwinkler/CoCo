package com.diplomski.mucnjak.coco.domain.mapper

interface UiMapper<DomainModel, UiModel> {
    fun mapToUiModel(domainModel: DomainModel): UiModel
}

interface NetworkMapper<NetworkModel, UiModel> {
    fun mapToNetworkModel(uiModel: UiModel): NetworkModel
}

interface DomainMapper<NetworkModel, DomainModel> {

    fun mapToDomainModel(networkModel: NetworkModel): DomainModel
}
