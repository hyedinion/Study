package com.example.intership_scrapproject_android.presentation.post_search

sealed class PostSearchEvent {
    data class GetPostSearchResult(val query: String) : PostSearchEvent()
    data class PostSearchQueryChange(val query: String) : PostSearchEvent()
    object ShowErrorToastHandled : PostSearchEvent()
}