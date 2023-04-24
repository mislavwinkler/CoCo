package com.diplomski.mucnjak.coco.domain.repositories.active_activity

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface ActiveActivityRepositoryModule {

    @Binds
    @Singleton
    fun bindActiveActivityRepository(repository: ActiveActivityRepositoryImpl): ActiveActivityRepository
}