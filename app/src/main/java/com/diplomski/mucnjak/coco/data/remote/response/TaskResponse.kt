package com.diplomski.mucnjak.coco.data.remote.response

import com.diplomski.mucnjak.coco.extensions.empty
import com.google.firebase.firestore.PropertyName

data class TaskResponse(
    @get:PropertyName("tekst_zadatka")
    @set:PropertyName("tekst_zadatka")
    var description: String = String.empty,
    @get:PropertyName("Odgovor")
    @set:PropertyName("Odgovor")
    var answer: List<AnswerResponse> = emptyList(),
)
