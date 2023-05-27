package com.diplomski.mucnjak.coco.ui.splash

import com.diplomski.mucnjak.coco.domain.use_case.get_activity.GetActivity
import com.diplomski.mucnjak.coco.shared.DoNothing
import com.diplomski.mucnjak.coco.shared.NoNavigationEvent
import com.diplomski.mucnjak.coco.shared.NoState
import com.diplomski.mucnjak.coco.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val getActivity: GetActivity,
) : BaseViewModel<NoState, NoNavigationEvent>(NoState) {

    suspend fun fetchConfiguration() {
        try {
            getActivity()
        } catch (e: Exception) {
            DoNothing
        }
    }
}