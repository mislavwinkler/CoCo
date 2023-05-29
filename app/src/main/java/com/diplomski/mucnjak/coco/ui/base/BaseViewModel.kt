package com.diplomski.mucnjak.coco.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

abstract class BaseViewModel<State, NavigationEvent>(initialState: State) : ViewModel() {

    private val uiState =  MutableStateFlow(initialState)
    val state: StateFlow<State> = uiState

    private val navigationEventMutableFlow =  MutableStateFlow<NavigationEvent?>(null)
    val navigationEvent: StateFlow<NavigationEvent?> = navigationEventMutableFlow

    protected fun updateState(update: (previousState: State) -> State) = uiState.update(update)

    protected fun setState(update: (previousState: State) -> State) {
        val state = uiState.value
        viewModelScope.launch {
            uiState.emit(update(state))
        }
    }

    protected fun setNavigationEvent(event: NavigationEvent) {
        navigationEventMutableFlow.update { event }
    }

    fun clearNavigationEvent() {
        navigationEventMutableFlow.update { null }
    }
}