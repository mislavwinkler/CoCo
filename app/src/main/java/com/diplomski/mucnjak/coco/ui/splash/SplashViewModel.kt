package com.diplomski.mucnjak.coco.ui.splash

import androidx.lifecycle.viewModelScope
import com.diplomski.mucnjak.coco.data.remote.exceptions.NoDocumentException
import com.diplomski.mucnjak.coco.domain.use_case.get_activity.GetActivity
import com.diplomski.mucnjak.coco.domain.use_case.release_activity.ReleaseActivity
import com.diplomski.mucnjak.coco.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val getActivity: GetActivity,
    private val releaseActivity: ReleaseActivity,
) : BaseViewModel<SplashState>(SplashState.Initial) {

    fun initializeActivity() {
        updateState { SplashState.Loading }
        viewModelScope.launch {
            try {
                val activity = getActivity()

                updateState {
                    SplashState.Loaded(
                        numOfStudents = activity.numOfStudent,
                        topic = activity.topic,
                        subTopic = activity.subTopic
                    )
                }
            } catch (e: NoDocumentException) {
                updateState { SplashState.Initial }
            }
        }
    }

    fun clearActivity() {
        updateState { SplashState.Loading }
        viewModelScope.launch {
            releaseActivity()
            updateState { SplashState.Initial }
        }
    }
}