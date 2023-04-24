package com.diplomski.mucnjak.coco.domain.interactor.release_activity_config

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface ReleaseActivityConfigInteractorModule {

    @Binds
    fun bindReleaseActivityConfigInteractor(
        interactor: ReleaseActivityConfigInteractorImpl
    ): ReleaseActivityConfigInteractor
}