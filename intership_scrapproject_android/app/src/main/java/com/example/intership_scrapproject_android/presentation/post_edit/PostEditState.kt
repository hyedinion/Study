package com.example.intership_scrapproject_android.presentation.post_edit

import androidx.paging.PagingData
import com.example.intership_scrapproject_android.data.remote.response.BlogSearchItem
import kotlinx.coroutines.flow.Flow

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
