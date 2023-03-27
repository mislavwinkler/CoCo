package com.diplomski.mucnjak.coco.domain.use_case.get_lessons

import com.diplomski.mucnjak.coco.data.remote.response.LessonResponse
import com.diplomski.mucnjak.coco.data.ui.Lesson
import com.diplomski.mucnjak.coco.domain.mapper.lesson_mapper.LessonMappers
import com.google.firebase.firestore.FirebaseFirestore
import com.jakov.trakt.moviestraktapp.shared.dispatcher.Dispatcher
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetLessonsUsecase @Inject constructor(
    private val firestore: FirebaseFirestore,
    private val lessonMapper: LessonMappers.LessonMapper,
    private val dispatcher: Dispatcher,
) : GetLessons {

    override suspend operator fun invoke(): List<Lesson> = withContext(dispatcher.io) {
        firestore.collectionGroup("lekcija").get().await().toObjects(LessonResponse::class.java)
            .map { lessonMapper.mapToUiModel(it) }
    }
}