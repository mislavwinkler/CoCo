package com.diplomski.mucnjak.coco.ui.common

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.diplomski.mucnjak.coco.ui.base.BaseViewModel

@Composable
fun <State, ViewModel: BaseViewModel<State>> ViewModel.OnState(
    onState: @Composable (state: State) -> Unit
) {
    val state by state.collectAsState()
    onState(state)
}