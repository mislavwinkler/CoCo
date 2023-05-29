package com.diplomski.mucnjak.coco.domain.repositories.state_machine

import kotlinx.coroutines.flow.Flow

interface StateMachineRepository {
    val navigate: Flow<State>
    suspend fun reset()
    suspend fun confirmNextStep(studentIndex: Int)
    suspend fun getNextDisplayTime(): Int
    suspend fun revokeNextStepConfirmation(studentIndex: Int)
}