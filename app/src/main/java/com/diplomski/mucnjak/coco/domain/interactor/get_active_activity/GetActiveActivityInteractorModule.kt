package com.diplomski.mucnjak.coco.domain.interactor.get_active_activity

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface GetActiveActivityInteractorModule {

    @Binds
    fun bindGetActiveActivityInteractor(
        interactor: GetActiveActivityInteractorImpl
    ): GetActiveActivityInteractor
}