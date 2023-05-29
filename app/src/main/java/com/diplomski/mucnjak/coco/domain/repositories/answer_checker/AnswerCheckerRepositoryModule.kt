package com.diplomski.mucnjak.coco.domain.repositories.answer_checker

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface AnswerCheckerRepositoryModule {

    @Singleton
    @Binds
    fun bindAnswerCheckerRepository(repositoryImpl: AnswerCheckerRepositoryImpl): AnswerCheckerRepository
}