package com.diplomski.mucnjak.coco.domain.use_case.confirm_next_step

import com.diplomski.mucnjak.coco.domain.repositories.state_machine.StateMachineRepository
import javax.inject.Inject

class ConfirmNextStepUseCase @Inject constructor(
    private val stateMachineRepository: StateMachineRepository
) : ConfirmNextStep {

    override suspend fun invoke(studentIndex: Int): Unit =
        stateMachineRepository.confirmNextStep(studentIndex)
}