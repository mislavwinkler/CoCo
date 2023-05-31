package com.diplomski.mucnjak.coco.domain.get_string

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object GetStringModule {

    @Provides
    fun provideGetString(
        @ApplicationContext context: Context
    ): GetString = GetStringUseCase(context)
}
