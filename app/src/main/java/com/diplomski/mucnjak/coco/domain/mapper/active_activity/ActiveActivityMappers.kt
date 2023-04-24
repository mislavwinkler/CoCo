package com.diplomski.mucnjak.coco.domain.mapper.active_activity

import com.diplomski.mucnjak.coco.data.domain.ActiveActivityDomainModel
import com.diplomski.mucnjak.coco.data.domain.Id
import com.diplomski.mucnjak.coco.data.remote.response.ActiveActivityResponse
import com.diplomski.mucnjak.coco.domain.mapper.DomainMapper

interface ActiveActivityMappers {


    interface ActiveActivityDomainMapper :
        DomainMapper<Pair<Id, ActiveActivityResponse>, ActiveActivityDomainModel>
}