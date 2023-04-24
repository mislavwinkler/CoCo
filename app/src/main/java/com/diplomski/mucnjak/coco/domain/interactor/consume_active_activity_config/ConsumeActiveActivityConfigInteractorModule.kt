package com.diplomski.mucnjak.coco.domain.interactor.consume_active_activity_config

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface ConsumeActiveActivityConfigInteractorModule {

    @Binds
    fun bindConsumeActiveActivityConfigInteractor(
        interactor: ConsumeActiveActivityConfigInteractorImpl
    ): ConsumeActiveActivityConfigInteractor
}
