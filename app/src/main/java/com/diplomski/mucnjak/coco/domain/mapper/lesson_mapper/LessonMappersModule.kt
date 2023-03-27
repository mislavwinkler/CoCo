package com.diplomski.mucnjak.coco.domain.mapper.lesson_mapper

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface LessonMappersModule {

    @Binds
    fun bindLessonMapper(mapper: LessonMapper): LessonMappers.LessonMapper
}