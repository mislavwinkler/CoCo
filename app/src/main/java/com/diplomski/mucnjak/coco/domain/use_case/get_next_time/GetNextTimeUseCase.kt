package com.diplomski.mucnjak.coco.domain.use_case.get_next_time

import com.diplomski.mucnjak.coco.domain.repositories.state_machine.StateMachineRepository
import javax.inject.Inject

class GetNextTimeUseCase @Inject constructor(
    private val stateMachineRepository: StateMachineRepository,
) : GetNextTime {


    override suspend fun invoke(): Int = stateMachineRepository.getNextDisplayTime()
}