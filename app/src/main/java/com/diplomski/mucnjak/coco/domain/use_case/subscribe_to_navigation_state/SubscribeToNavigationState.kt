package com.diplomski.mucnjak.coco.domain.use_case.subscribe_to_navigation_state

import com.diplomski.mucnjak.coco.domain.repositories.state_machine.State
import kotlinx.coroutines.flow.Flow

interface SubscribeToNavigationState {

    operator fun invoke(): Flow<State>
}