package com.diplomski.mucnjak.coco.shared.dispatcher

import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

data class DispatcherImpl @Inject constructor(
    override val io: CoroutineDispatcher,
    override val default: CoroutineDispatcher,
    override val main: CoroutineDispatcher
) : Dispatcher
