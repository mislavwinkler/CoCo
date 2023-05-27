package com.diplomski.mucnjak.coco.domain.repositories.state_machine

enum class State {
    NAME_INPUT,
    SETUP,
    WELCOME,
    SOLVING,
    INCORRECT_SOLUTION_NOTE,
    DISCUSSION,
    RETRY_NOTE,
    FINISH_NOTE,
    SOLUTIONS
}