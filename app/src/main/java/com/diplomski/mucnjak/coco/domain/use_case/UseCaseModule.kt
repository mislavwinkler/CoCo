package com.diplomski.mucnjak.coco.domain.use_case

import com.diplomski.mucnjak.coco.domain.use_case.get_activity.GetActivity
import com.diplomski.mucnjak.coco.domain.use_case.get_activity.GetActivityUseCase
import com.diplomski.mucnjak.coco.domain.use_case.get_all_answers.GetAllAnswers
import com.diplomski.mucnjak.coco.domain.use_case.get_all_answers.GetAllAnswersUseCase
import com.diplomski.mucnjak.coco.domain.use_case.get_available_question.GetAvailableQuestion
import com.diplomski.mucnjak.coco.domain.use_case.get_available_question.GetAvailableQuestionUseCase
import com.diplomski.mucnjak.coco.domain.use_case.get_num_of_students.GetNumOfStudents
import com.diplomski.mucnjak.coco.domain.use_case.get_num_of_students.GetNumOfStudentsUseCase
import com.diplomski.mucnjak.coco.domain.use_case.release_activity.ReleaseActivity
import com.diplomski.mucnjak.coco.domain.use_case.release_activity.ReleaseActivityUseCase
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
}