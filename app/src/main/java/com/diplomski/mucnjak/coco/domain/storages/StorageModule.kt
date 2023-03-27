package com.diplomski.mucnjak.coco.domain.storages

import com.diplomski.mucnjak.coco.data.ui.Student
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface StorageModule {

    @Binds
    fun bindStudentStorage(storage: StudentStorage): Storage<Student>
}