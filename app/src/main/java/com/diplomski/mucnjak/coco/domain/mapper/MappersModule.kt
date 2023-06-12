package com.diplomski.mucnjak.coco.domain.mapper

import com.diplomski.mucnjak.coco.domain.mapper.active_activity.ActiveActivityDomainMapper
import com.diplomski.mucnjak.coco.domain.mapper.active_activity.ActiveActivityMappers
import com.diplomski.mucnjak.coco.domain.mapper.activity.ActivityMapper
import com.diplomski.mucnjak.coco.domain.mapper.activity.ActivityMappers
import com.diplomski.mucnjak.coco.domain.mapper.analytics.AnalyticsMappers
import com.diplomski.mucnjak.coco.domain.mapper.analytics.AnalyticsNetworkMapper
import com.diplomski.mucnjak.coco.domain.mapper.answer.AnswerMappers
import com.diplomski.mucnjak.coco.domain.mapper.answer.AnswersMapper
import com.diplomski.mucnjak.coco.domain.mapper.question.QuestionsMapper
import com.diplomski.mucnjak.coco.domain.mapper.question.QuestionsMappers
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface MappersModule {

    @Binds
    fun bindActiveActivityDomainMapper(
        mapper: ActiveActivityDomainMapper
    ): ActiveActivityMappers.ActiveActivityDomainMapper

    @Binds
    fun bindActivityMapper(mapper: ActivityMapper): ActivityMappers.ActivityMapper

    @Binds
    fun bindQuestionMapper(mapper: QuestionsMapper): QuestionsMappers.QuestionMapper

    @Binds
    fun bindAnswerMapper(mapper: AnswersMapper): AnswerMappers.AnswersMapper

    @Binds
    fun bindAnalyticsMapper(mapper: AnalyticsNetworkMapper): AnalyticsMappers.AnalyticsNetworkMapper
}
