package com.diplomski.mucnjak.coco.ui.splash

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.diplomski.mucnjak.coco.domain.use_case.get_lessons.GetLessons
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val getLessons: GetLessons
) : ViewModel() {


    init {
        viewModelScope.launch {
            getLessons().forEach {
                Log.d("jakovLogs", "jakovLogs ${it.topicName}")
            }
        }
    }
}