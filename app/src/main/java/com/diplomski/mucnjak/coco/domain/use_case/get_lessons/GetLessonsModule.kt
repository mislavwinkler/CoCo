package com.diplomski.mucnjak.coco.domain.use_case.get_lessons

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface GetLessonsModule {

    @Binds
    fun bindGetLessonsUseCase(useCase: GetLessonsUsecase): GetLessons
}