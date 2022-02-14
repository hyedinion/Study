package com.example.intership_scrapproject_android.presentation.post_detail

import com.example.intership_scrapproject_android.data.local.Post

sealed class PostDetailEvent {
    data class PostDeleteButtonClicked(val post : Post) : PostDetailEvent()
    object ShowDeleteErrorToastHandled : PostDetailEvent()
    object MoveScreenHandled : PostDetailEvent()
    object EmptyLinkDetect : PostDetailEvent()
    object EmptyLinkToastHandled : PostDetailEvent()
}