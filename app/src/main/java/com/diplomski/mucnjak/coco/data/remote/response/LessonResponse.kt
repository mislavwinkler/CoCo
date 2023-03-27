package com.diplomski.mucnjak.coco.data.remote.response

import com.diplomski.mucnjak.coco.extensions.empty
import com.google.firebase.firestore.PropertyName

data class LessonResponse(
    @get:PropertyName("tema")
    @set:PropertyName("tema")
    var topic: String = String.empty,
    @get:PropertyName("Podtema")
    @set:PropertyName("Podtema")
    var subTopic: List<SubTopicResponse> = emptyList(),
)
