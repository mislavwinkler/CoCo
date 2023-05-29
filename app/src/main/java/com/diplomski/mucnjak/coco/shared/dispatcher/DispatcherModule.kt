package com.diplomski.mucnjak.coco.shared.dispatcher

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers

@Module
@InstallIn(SingletonComponent::class)
class DispatcherModule {
    @Provides
    fun provideDispatcher(): Dispatcher =
        DispatcherImpl(Dispatchers.IO, Dispatchers.Default, Dispatchers.Main)
}