package com.example.intership_scrapproject_android.presentation.blog_search_scrap

import androidx.paging.PagingData
import com.example.intership_scrapproject_android.data.remote.response.BlogSearchItem
import kotlinx.coroutines.flow.Flow

data class BlogScrapState(
    val blogScrapTitle : String = "",
    val blogScrapDescription : String = "",
    val saveBlogSuccess : Boolean = false,
    val showInsertErrorToastMessage : Boolean = false,
    val insertErrorToastMessage : String = "",
    val showBlankToastMessage : Boolean = false,
    val blankErrorToastMessage : String = "",
    val initBlogScrapPost : Boolean = true,
)
