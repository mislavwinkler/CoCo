package com.diplomski.mucnjak.coco.domain.interactor.post_analytics_results

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface PostAnalyticsResultsInteractorModule {

    @Binds
    fun bindPostAnalyticsResultsInteractor(interactor: PostAnalyticsResultsInteractorImpl): PostAnalyticsResultsInteractor
}