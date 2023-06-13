package com.diplomski.mucnjak.coco.analytics

import com.diplomski.mucnjak.coco.data.ui.Answer

interface Analytics {
    fun sendActivityAnalytics()
    fun sendStudentRotation(studentIndex: Int, screen: String)
    fun sendResults()
    fun sendScreenStart(screen: String, studentIndex: Int? = null)
    fun sendAnswerSelected(studentIndex: Int, answer: Answer)
    fun sendAnswerUnselected(studentIndex: Int, answer: Answer)
    fun sendStudentReady(studentIndex: Int, screen: String)
    fun sendStudents()
    fun sendStudentUnready(studentIndex: Int, screen: String)
}