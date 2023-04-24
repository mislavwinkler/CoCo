package com.diplomski.mucnjak.coco.domain.repositories.question

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface QuestionsRepositoryModule {

    @Binds
    @Singleton
    fun bindQuestionsRepository(repository: QuestionsRepositoryImpl): QuestionsRepository
}