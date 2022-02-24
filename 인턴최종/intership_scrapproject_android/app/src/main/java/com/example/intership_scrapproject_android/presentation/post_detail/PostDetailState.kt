package com.example.intership_scrapproject_android.presentation.post_detail

data class PostDetailState (
    val deletePostSuccess : Boolean = false,
    val showDeleteErrorToastMessage : Boolean = false,
    val DeleteErrorToastMessage : String = "",
    val showEmptyLinkErrorToastMessage : Boolean = false,
    val EmptyLinkErrorToastMessage : String = "link가 존재하지 않습니다.",
)
