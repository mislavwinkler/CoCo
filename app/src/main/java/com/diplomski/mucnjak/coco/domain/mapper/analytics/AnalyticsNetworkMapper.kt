package com.diplomski.mucnjak.coco.domain.mapper.analytics

import com.diplomski.mucnjak.coco.data.domain.ResultsDomainModel
import com.diplomski.mucnjak.coco.data.remote.request.AnalyticsRequest
import javax.inject.Inject

class AnalyticsNetworkMapper @Inject constructor() : AnalyticsMappers.AnalyticsNetworkMapper {

    override fun mapToNetworkModel(domainModel: ResultsDomainModel): AnalyticsRequest =
        AnalyticsRequest(
            date = domainModel.date,
            group = domainModel.group + 1,
            subtopic = domainModel.subtopic,
            topic = domainModel.topic,
            results = domainModel.results.map { (_, name, accuracies, initialResolutionTimes) ->
                val studentResultsMap = mutableMapOf<String, Any>()
                accuracies.forEachIndexed { index, accuracy ->
                    studentResultsMap["accuracy$index"] = accuracy
                }
                initialResolutionTimes.forEachIndexed { index, resolutionChangeTimes ->
                    studentResultsMap["resolutionTime$index"] = buildList {
                        add(resolutionChangeTimes[0])
                        val changeTimes = resolutionChangeTimes.drop(1).run {
                            if (size % 2 == 1) {
                                dropLast(1)
                            } else {
                                this
                            }
                        }
                        for (i in changeTimes.indices step 2) {
                            add(changeTimes[i + 1] - changeTimes[i])
                        }
                    }

                }
                studentResultsMap["name"] = name
                studentResultsMap
            },
            discussionTimes = domainModel.discussionTimes,
        )
}