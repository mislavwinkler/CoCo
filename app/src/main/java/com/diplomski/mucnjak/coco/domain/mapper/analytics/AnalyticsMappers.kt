package com.diplomski.mucnjak.coco.domain.mapper.analytics

import com.diplomski.mucnjak.coco.data.domain.ResultsDomainModel
import com.diplomski.mucnjak.coco.data.remote.request.AnalyticsRequest
import com.diplomski.mucnjak.coco.domain.mapper.NetworkMapper

interface AnalyticsMappers {

    interface AnalyticsNetworkMapper : NetworkMapper<ResultsDomainModel, AnalyticsRequest>
}