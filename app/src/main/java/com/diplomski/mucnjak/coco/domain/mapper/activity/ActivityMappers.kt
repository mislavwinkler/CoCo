package com.diplomski.mucnjak.coco.domain.mapper.activity

import com.diplomski.mucnjak.coco.data.domain.ActiveActivityDomainModel
import com.diplomski.mucnjak.coco.data.ui.Activity
import com.diplomski.mucnjak.coco.domain.mapper.UiMapper

interface ActivityMappers {

    interface ActivityMapper : UiMapper<ActiveActivityDomainModel, Activity>
}