package com.diplomski.mucnjak.coco.domain.use_case.subscribe_to_navigation_state

import com.diplomski.mucnjak.coco.domain.repositories.state_machine.State
import com.diplomski.mucnjak.coco.domain.repositories.state_machine.StateMachineRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SubscribeToNavigationStateUseCase @Inject constructor(
    private val stateMachineRepository: StateMachineRepository,
) : SubscribeToNavigationState {

    override fun invoke(): Flow<State> = stateMachineRepository.navigate
}