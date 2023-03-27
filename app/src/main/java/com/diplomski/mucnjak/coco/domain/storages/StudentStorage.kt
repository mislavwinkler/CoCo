package com.diplomski.mucnjak.coco.domain.storages

import com.diplomski.mucnjak.coco.data.ui.Student
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class StudentStorage @Inject constructor() : ListStorage<Student>()
