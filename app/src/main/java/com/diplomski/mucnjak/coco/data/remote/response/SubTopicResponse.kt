package com.diplomski.mucnjak.coco.data.remote.response

import com.diplomski.mucnjak.coco.extensions.empty
import com.google.firebase.firestore.PropertyName

data class SubTopicResponse(
    @get:PropertyName("naziv")
    @set:PropertyName("naziv")
    var title: String = String.empty,
    @get:PropertyName("Zadatak")
    @set:PropertyName("Zatatak")
    var tasks: List<TaskResponse> = emptyList(),
)
