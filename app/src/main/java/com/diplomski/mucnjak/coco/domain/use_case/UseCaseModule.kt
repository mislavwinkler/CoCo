package com.diplomski.mucnjak.coco.domain.use_case

import com.diplomski.mucnjak.coco.domain.use_case.confirm_next_step.ConfirmNextStep
import com.diplomski.mucnjak.coco.domain.use_case.confirm_next_step.ConfirmNextStepUseCase
import com.diplomski.mucnjak.coco.domain.use_case.get_activity.GetActivity
import com.diplomski.mucnjak.coco.domain.use_case.get_activity.GetActivityUseCase
import com.diplomski.mucnjak.coco.domain.use_case.get_all_answers.GetAllAnswers
import com.diplomski.mucnjak.coco.domain.use_case.get_all_answers.GetAllAnswersUseCase
import com.diplomski.mucnjak.coco.domain.use_case.get_available_question.GetAvailableQuestion
import com.diplomski.mucnjak.coco.domain.use_case.get_available_question.GetAvailableQuestionUseCase
import com.diplomski.mucnjak.coco.domain.use_case.get_next_time.GetNextTime
import com.diplomski.mucnjak.coco.domain.use_case.get_next_time.GetNextTimeUseCase
import com.diplomski.mucnjak.coco.domain.use_case.get_num_of_students.GetNumOfStudents
import com.diplomski.mucnjak.coco.domain.use_case.get_num_of_students.GetNumOfStudentsUseCase
import com.diplomski.mucnjak.coco.domain.use_case.get_student_name.GetStudentName
import com.diplomski.mucnjak.coco.domain.use_case.get_student_name.GetStudentNameUseCase
import com.diplomski.mucnjak.coco.domain.use_case.get_student_rotation.SubscribeToStudentRotation
import com.diplomski.mucnjak.coco.domain.use_case.get_student_rotation.SubscribeToStudentRotationUseCase
import com.diplomski.mucnjak.coco.domain.use_case.is_activity_loaded.IsActivityLoaded
import com.diplomski.mucnjak.coco.domain.use_case.is_activity_loaded.IsActivityLoadedUseCase
import com.diplomski.mucnjak.coco.domain.use_case.release_activity.ReleaseActivity
import com.diplomski.mucnjak.coco.domain.use_case.release_activity.ReleaseActivityUseCase
import com.diplomski.mucnjak.coco.domain.use_case.reset_state_machine.ResetStateMachine
import com.diplomski.mucnjak.coco.domain.use_case.reset_state_machine.ResetStateMachineUseCase
import com.diplomski.mucnjak.coco.domain.use_case.rotate_student_screen.RotateStudentScreen
import com.diplomski.mucnjak.coco.domain.use_case.rotate_student_screen.RotateStudentScreenUseCase
import com.diplomski.mucnjak.coco.domain.use_case.store_student.StoreStudent
import com.diplomski.mucnjak.coco.domain.use_case.store_student.StoreStudentUseCase
import com.diplomski.mucnjak.coco.domain.use_case.subscribe_to_navigation_state.SubscribeToNavigationState
import com.diplomski.mucnjak.coco.domain.use_case.subscribe_to_navigation_state.SubscribeToNavigationStateUseCase
import com.diplomski.mucnjak.coco.domain.use_case.subscribe_to_timer_ticks.SubscribeToTimerTicks
import com.diplomski.mucnjak.coco.domain.use_case.subscribe_to_timer_ticks.SubscribeToTimerTicksUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface UseCaseModule {

    @Binds
    fun bindGetActivityUseCase(useCase: GetActivityUseCase): GetActivity

    @Binds
    fun bindGetAllAnswersUseCase(useCase: GetAllAnswersUseCase): GetAllAnswers

    @Binds
    fun bindGetAvailableQuestionUseCase(useCase: GetAvailableQuestionUseCase): GetAvailableQuestion

    @Binds
    fun bindReleaseActivityUseCase(useCase: ReleaseActivityUseCase): ReleaseActivity

    @Binds
    fun bindGetNumOfStudents(useCase: GetNumOfStudentsUseCase): GetNumOfStudents

    @Binds
    fun bindIsActivityLoaded(useCase: IsActivityLoadedUseCase): IsActivityLoaded

    @Binds
    fun bindStoreStudent(useCase: StoreStudentUseCase): StoreStudent

    @Binds
    fun getStudentRotation(useCase: SubscribeToStudentRotationUseCase): SubscribeToStudentRotation

    @Binds
    fun getStudentName(useCase: GetStudentNameUseCase): GetStudentName

    @Binds
    fun rotateStudentScreen(useCase: RotateStudentScreenUseCase): RotateStudentScreen

    @Binds
    fun subscribeToTimerTicks(useCase: SubscribeToTimerTicksUseCase): SubscribeToTimerTicks

    @Binds
    fun subscribeToNavigationState(useCase: SubscribeToNavigationStateUseCase): SubscribeToNavigationState

    @Binds
    fun resetStateMachine(useCase: ResetStateMachineUseCase): ResetStateMachine

    @Binds
    fun confirmNextStep(useCase: ConfirmNextStepUseCase): ConfirmNextStep

    @Binds
    fun bindGetNextTime(useCase: GetNextTimeUseCase): GetNextTime
}
