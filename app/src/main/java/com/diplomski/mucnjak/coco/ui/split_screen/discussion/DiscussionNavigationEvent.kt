package com.diplomski.mucnjak.coco.ui.split_screen.discussion

sealed class DiscussionNavigationEvent {
    object NavigateToRetry : DiscussionNavigationEvent()
}