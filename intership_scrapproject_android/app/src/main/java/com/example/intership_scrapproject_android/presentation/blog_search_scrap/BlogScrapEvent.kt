package com.example.intership_scrapproject_android.presentation.blog_search_scrap

import com.example.intership_scrapproject_android.data.remote.response.BlogSearchItem
import com.example.intership_scrapproject_android.presentation.blog_search.BlogSearchEvent

sealed class BlogScrapEvent {
    data class InitBlogScrapTitleDescription(val title : String, val description : String) : BlogScrapEvent()
    data class BlogScrapTitleChange(val title : String) : BlogScrapEvent()
    data class BlogScrapDescriptionChange(val description : String) : BlogScrapEvent()
    data class BlogScrapButtonClicked(val blogSearchItem: BlogSearchItem, val keyword : String) : BlogScrapEvent()
    object ShowInsertErrorToastHandled : BlogScrapEvent()
    object ShowBlankErrorToastHandled : BlogScrapEvent()
    object MoveScreenHandled : BlogScrapEvent()
}