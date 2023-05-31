package com.diplomski.mucnjak.coco.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.diplomski.mucnjak.coco.ui.base.BaseViewModel

@Composable
fun <State, NavigationEvent, ViewModel : BaseViewModel<State, NavigationEvent>> ViewModel.OnState(
    onState: @Composable (state: State) -> Unit
) {
    val state by state.collectAsState()
    onState(state)
}

@Composable
fun <State, NavigationEvent, ViewModel : BaseViewModel<State, NavigationEvent>> ViewModel.OnNavigationEvent(
    onNavigationEvent: @Composable (event: NavigationEvent) -> Unit
) {
    val event by navigationEvent.collectAsState()
    event?.let { onNavigationEvent(it) }
    clearNavigationEvent()
}