package com.diplomski.mucnjak.coco.domain.mapper

interface UiMapper<DomainModel, UiModel> {
    fun mapToUiModel(domainModel: DomainModel): UiModel
}

interface DomainMapper<NetworkModel, DomainModel> {

    fun mapToDomainModel(networkModel: NetworkModel): DomainModel
}

interface NetworkMapper<DomainModel, NetworkModel> {

    fun mapToNetworkModel(domainModel: DomainModel): NetworkModel
}
