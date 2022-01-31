package com.example.intership_scrapproject_android.presentation.blog_search

import androidx.paging.PagingData
import com.example.intership_scrapproject_android.data.remote.response.BlogSearchItem
import kotlinx.coroutines.flow.Flow

data class BlogSearchState(
    val isBlogSearchLayoutList : Boolean = true, //현재 설정창이 열려있는지 상태
    val blogSearchText : String = "",
    val blogSearchResult : Flow<PagingData<BlogSearchItem>>? = null,//scrap 목록 상태
    val isRefreshing : Boolean = false,
)
