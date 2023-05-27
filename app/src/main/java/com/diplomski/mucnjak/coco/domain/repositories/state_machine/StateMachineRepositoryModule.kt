package com.diplomski.mucnjak.coco.domain.repositories.state_machine

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface StateMachineRepositoryModule {

    @Binds
    @Singleton
    fun bindStateMachineRepository(repository: StateMachineRepositoryImpl): StateMachineRepository
}