package com.diplomski.mucnjak.coco.domain.use_case.add_incorrect_answer_info

import com.diplomski.mucnjak.coco.data.ui.Answer
import com.diplomski.mucnjak.coco.domain.repositories.answer_checker.AnswerCheckerRepository
import javax.inject.Inject

class AddIncorrectAnswerInfoUseCase @Inject constructor(
    private val answerCheckerRepository: AnswerCheckerRepository
) : AddIncorrectAnswerInfo {

    override fun invoke(answers: List<Answer>) = answerCheckerRepository.addIncorrectAnswerInfo(
        answers = answers
    )
}