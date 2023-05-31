package com.diplomski.mucnjak.coco.domain.repositories.uuid

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

private const val TABLET_CONFIG_SHARED_PREFS = "com.diplomski.mucnjak.coco.TABLET_CONFIG"

@Module
@InstallIn(SingletonComponent::class)
class UuidRepositoryModule {

    @Provides
    @Singleton
    fun provideUuidRepository(@ApplicationContext context: Context): UuidRepository =
        UuidRepositoryImpl(
            context.getSharedPreferences(
                TABLET_CONFIG_SHARED_PREFS,
                Context.MODE_PRIVATE
            )
        )
}