package com.example.intership_scrapproject_android.presentation.post_search

import com.example.intership_scrapproject_android.data.local.Post

data class PostSearchState(
    val postSearchText : String = "",
    val postSearchQuery : String = "",
    val postSearchResult : List<Post> = emptyList(),
    val showToastMessage : Boolean = false,
    val toastErrorMessage : String = "",
)
