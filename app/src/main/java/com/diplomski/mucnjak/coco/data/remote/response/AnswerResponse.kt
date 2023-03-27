package com.diplomski.mucnjak.coco.data.remote.response

import com.diplomski.mucnjak.coco.extensions.empty
import com.google.firebase.firestore.PropertyName

data class AnswerResponse(
    @get:PropertyName("tekst_odgovora")
    @set:PropertyName("tekst_odgovora")
    var description: String = String.empty
)
