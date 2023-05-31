package com.diplomski.mucnjak.coco.domain.get_string

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class GetStringUseCase @Inject constructor(
    @ApplicationContext private val context: Context
) : GetString {

    override fun invoke(stringRes: Int): String = context.getString(stringRes)

    override fun invoke(stringRes: Int, vararg formatArgs: Any): String =
        context.getString(stringRes, *formatArgs)
}