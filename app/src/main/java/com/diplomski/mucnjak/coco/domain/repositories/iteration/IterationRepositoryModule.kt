package com.diplomski.mucnjak.coco.domain.repositories.iteration

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface IterationRepositoryModule {

    @Binds
    @Singleton
    fun bindIterationRepository(repository: IterationRepositoryImpl): IterationRepository
}