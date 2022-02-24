package com.example.intership_scrapproject_android.presentation.post_edit

data class PostEditState(
    val postEditTitle : String = "",
    val postEditDescription : String = "",
    val EditPostSuccess : Boolean = false,
    val showInsertErrorToastMessage : Boolean = false,
    val insertErrorToastMessage : String = "",
    val showBlankToastMessage : Boolean = false,
    val blankErrorToastMessage : String = "",
    val initEditPost : Boolean = true,
    val editModeActivate : Boolean = false,
)
