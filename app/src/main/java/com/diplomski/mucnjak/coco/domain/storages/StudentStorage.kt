package com.diplomski.mucnjak.coco.domain.storages

import com.diplomski.mucnjak.coco.data.ui.Student
import com.diplomski.mucnjak.coco.domain.storages.base.BaseListStorage
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class StudentStorage @Inject constructor() : BaseListStorage<Student>()
