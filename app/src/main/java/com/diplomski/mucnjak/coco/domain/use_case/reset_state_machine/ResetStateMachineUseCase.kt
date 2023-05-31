package com.diplomski.mucnjak.coco.domain.use_case.reset_state_machine

import com.diplomski.mucnjak.coco.domain.repositories.state_machine.StateMachineRepository
import javax.inject.Inject

class ResetStateMachineUseCase @Inject constructor(
    private val stateMachineRepository: StateMachineRepository
) : ResetStateMachine {

    override suspend fun invoke(): Unit = stateMachineRepository.reset()
}