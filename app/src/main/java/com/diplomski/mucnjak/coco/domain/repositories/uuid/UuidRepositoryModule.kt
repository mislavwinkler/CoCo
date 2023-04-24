package com.diplomski.mucnjak.coco.domain.repositories.uuid

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UuidRepositoryModule {

    @Provides
    @Singleton
    fun provideUuidRepository(@ApplicationContext context: Context): UuidRepository =
        UuidRepositoryImpl(context.getSharedPreferences("com.diplomski.mucnjak.coco.TABLET_CONFIG", Context.MODE_PRIVATE))
}