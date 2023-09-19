package com.diplomski.mucnjak.coco.ui.split_screen.incorrect_solution

import android.text.format.DateUtils
import androidx.lifecycle.viewModelScope
import com.diplomski.mucnjak.coco.analytics.Analytics
import com.diplomski.mucnjak.coco.domain.repositories.state_machine.State
import com.diplomski.mucnjak.coco.domain.use_case.confirm_next_step.ConfirmNextStep
import com.diplomski.mucnjak.coco.domain.use_case.get_next_time.GetNextTime
import com.diplomski.mucnjak.coco.domain.use_case.rotate_student_screen.RotateStudentScreen
import com.diplomski.mucnjak.coco.domain.use_case.subscribe_to_navigation_state.SubscribeToNavigationState
import com.diplomski.mucnjak.coco.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class IncorrectSolutionViewModel @Inject constructor(
    private val rotateStudentScreen: RotateStudentScreen,
    private val confirmNextStep: ConfirmNextStep,
    private val analytics: Analytics,
    getNextTime: GetNextTime,
    subscribeToNavigationState: SubscribeToNavigationState,
) : BaseViewModel<IncorrectSolutionState, IncorrectSolutionNavigationEvent>(
    IncorrectSolutionState.Initial
) {

    init {
        viewModelScope.launch {
            launch {
                subscribeToNavigationState().collect {
                    if (it == State.DISCUSSION) {
                        setNavigationEvent(
                            event = IncorrectSolutionNavigationEvent.NavigateToDiscussion
                        )
                    }
                }
            }

            val nextTime = getNextTime()

            updateState {
                IncorrectSolutionState.Note(timeToDiscuss = (nextTime / 60).toString())
            }
        }


    }

    fun main() = runBlocking { // this: CoroutineScope
        launch { // pokreni novu korutinu i nastavi
            delay(1000L) // ne blokirajuća odgoda od 1 sekunde ()non-blocking delay for 1 second (default time unit is ms)
            println("World!") // print after delay
        }
        println("Hello") // main coroutine continues while a previous one is delayed
    }

    fun rotateScreen(studentIndex: Int) {
        analytics.sendStudentRotation(studentIndex, "Incorrect solution")
        viewModelScope.launch {
            rotateStudentScreen(studentIndex = studentIndex)
        }
    }

    fun confirmStudentNextStep(studentIndex: Int) {
        analytics.sendStudentReady(studentIndex, "Incorrect solution")
        viewModelScope.launch {
            confirmNextStep(studentIndex = studentIndex)
            updateState { state ->
                (state as? IncorrectSolutionState.Note)?.copy(isConfirmed = true) ?: state
            }
        }
    }

}