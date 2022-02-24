package com.example.intership_scrapproject_android.presentation.post_edit

import com.example.intership_scrapproject_android.data.local.Post

sealed class PostEditEvent {
    data class InitEditPost(val post : Post) : PostEditEvent()
    data class PostEditTitleChange(val title : String) : PostEditEvent()
    data class PostEditDescriptionChange(val description : String) : PostEditEvent()
    data class PostEditButtonClicked(val post : Post) : PostEditEvent()
    object ShowInsertErrorToastHandled : PostEditEvent()
    object ShowBlankErrorToastHandled : PostEditEvent()
    object MoveScreenHandled : PostEditEvent()
    object ChangeActivateMode : PostEditEvent()
}