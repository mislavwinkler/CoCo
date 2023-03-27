package com.diplomski.mucnjak.coco.ui.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

abstract class BaseViewModel<State>(initialState: State) : ViewModel() {

    private val uiState =  MutableStateFlow(initialState)
    val state: StateFlow<State> = uiState

    protected fun updateState(update: (previousState: State) -> State) = uiState.update(update)
}