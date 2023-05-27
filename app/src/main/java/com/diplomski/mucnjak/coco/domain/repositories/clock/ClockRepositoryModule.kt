package com.diplomski.mucnjak.coco.domain.repositories.clock

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface ClockRepositoryModule {

    @Binds
    @Singleton
    fun bindClockRepository(repository: ClockRepositoryImpl): ClockRepository
}