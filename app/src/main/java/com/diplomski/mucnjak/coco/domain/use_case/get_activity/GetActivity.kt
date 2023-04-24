package com.diplomski.mucnjak.coco.domain.use_case.get_activity

import com.diplomski.mucnjak.coco.data.ui.Activity

interface GetActivity {
    suspend operator fun invoke(): Activity
}