package com.diplomski.mucnjak.coco.ui.solutions

import androidx.lifecycle.viewModelScope
import com.diplomski.mucnjak.coco.data.ui.Activity
import com.diplomski.mucnjak.coco.data.ui.Question
import com.diplomski.mucnjak.coco.domain.use_case.get_activity.GetActivity
import com.diplomski.mucnjak.coco.domain.use_case.get_all_questions.GetAllQuestions
import com.diplomski.mucnjak.coco.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SolutionsViewModel @Inject constructor(
    getAllQuestions: GetAllQuestions,
    getActivity: GetActivity
) : BaseViewModel<SolutionsState, SolutionsNavigationEvent>(SolutionsState.Initial) {

    private lateinit var activity: Activity
    private lateinit var questions: List<Question>

    init {
        viewModelScope.launch {
            questions = getAllQuestions()
            activity = getActivity()
            setSolutionsState()
        }
    }

    fun confirmCongratulations() {
        setNavigationEvent(SolutionsNavigationEvent.NavigateToStart)
    }

    fun confirmSolutions() {
        updateState {
            SolutionsState.Congratulations
        }
    }

    fun returnToSolutions() {
        setSolutionsState()
    }

    private fun setSolutionsState() {
        updateState {
            SolutionsState.Solutions("${activity.topic} | ${activity.topic}", questions)
        }
    }
}