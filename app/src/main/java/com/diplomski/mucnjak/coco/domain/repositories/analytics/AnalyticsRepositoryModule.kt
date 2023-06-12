package com.diplomski.mucnjak.coco.domain.repositories.analytics

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface AnalyticsRepositoryModule {

    @Binds
    @Singleton
    fun bindAnalyticsRepository(repositoryImpl: AnalyticsRepositoryImpl): AnalyticsRepository
}