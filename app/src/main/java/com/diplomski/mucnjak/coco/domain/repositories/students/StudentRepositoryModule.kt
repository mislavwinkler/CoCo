package com.diplomski.mucnjak.coco.domain.repositories.students

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface StudentRepositoryModule {

    @Binds
    @Singleton
    fun bindStudentRepositoryModule(repository: StudentRepositoryImpl): StudentRepository
}