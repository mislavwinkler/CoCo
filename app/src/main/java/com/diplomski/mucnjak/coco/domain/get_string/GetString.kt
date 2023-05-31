package com.diplomski.mucnjak.coco.domain.get_string

import androidx.annotation.StringRes

interface GetString {

    operator fun invoke(@StringRes stringRes: Int): String

    operator fun invoke(@StringRes stringRes: Int, vararg formatArgs: Any): String
}