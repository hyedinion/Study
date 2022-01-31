package com.example.intership_scrapproject_android.presentation.blog_search

sealed class BlogSearchEvent {
    object BlogSearchLayoutChange : BlogSearchEvent() //Search Order이 바뀌면 event 전송
    data class GetBlogSearchResult(val query: String) : BlogSearchEvent() //search를 하면
    data class BlogSearchQueryChange(val query: String) : BlogSearchEvent() // 키워드 입력
    object Refreshing : BlogSearchEvent() //Search Order이 바뀌면 event 전송

}