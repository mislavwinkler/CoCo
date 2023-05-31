package com.diplomski.mucnjak.coco.domain.use_case.revoke_next_step_confirmation

import com.diplomski.mucnjak.coco.domain.repositories.state_machine.StateMachineRepository
import javax.inject.Inject

class RevokeNextStepConfirmationUseCase @Inject constructor(
    private val stateMachineRepository: StateMachineRepository
) : RevokeNextStepConfirmation {

    override suspend fun invoke(studentIndex: Int): Unit =
        stateMachineRepository.revokeNextStepConfirmation(studentIndex)
}