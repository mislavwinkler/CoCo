package com.diplomski.mucnjak.coco.ui.home

import androidx.lifecycle.viewModelScope
import com.diplomski.mucnjak.coco.data.remote.exceptions.NoDocumentException
import com.diplomski.mucnjak.coco.domain.use_case.get_activity.GetActivity
import com.diplomski.mucnjak.coco.domain.use_case.is_activity_loaded.IsActivityLoaded
import com.diplomski.mucnjak.coco.domain.use_case.release_activity.ReleaseActivity
import com.diplomski.mucnjak.coco.shared.NoNavigationEvent
import com.diplomski.mucnjak.coco.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getActivity: GetActivity,
    private val releaseActivity: ReleaseActivity,
    isActivityLoaded: IsActivityLoaded,
) : BaseViewModel<HomeState, NoNavigationEvent>(HomeState.Initial) {

    init {
        if (isActivityLoaded()) {
            loadActivity()
        } else {
            updateState { HomeState.Failed }
        }
    }

    fun loadActivity() {
        updateState { HomeState.Loading }
        viewModelScope.launch {
            try {
                val activity = getActivity()

                updateState {
                    HomeState.Loaded(
                        numOfStudents = activity.numOfStudent,
                        topic = activity.topic,
                        subTopic = activity.subTopic
                    )
                }
            } catch (e: NoDocumentException) {
                updateState { HomeState.Failed }
            }
        }
    }

    fun clearActivity() {
        updateState { HomeState.Loading }
        viewModelScope.launch {
            releaseActivity()
            updateState { HomeState.Failed }
        }
    }
}