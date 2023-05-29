package com.diplomski.mucnjak.coco.ui.split_screen.finish_note

import androidx.lifecycle.viewModelScope
import com.diplomski.mucnjak.coco.shared.NoState
import com.diplomski.mucnjak.coco.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FinishNoteViewModel @Inject constructor() :
    BaseViewModel<NoState, FinishNoteNavigationEvent>(NoState) {

    init {
        viewModelScope.launch {
            delay(4000)
            setNavigationEvent(FinishNoteNavigationEvent.NavigateToSolutions)
        }
    }
}