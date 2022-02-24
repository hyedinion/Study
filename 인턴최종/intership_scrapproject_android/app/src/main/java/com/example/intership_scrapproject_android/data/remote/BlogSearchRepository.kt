package com.example.intership_scrapproject_android.data.remote

import androidx.paging.PagingData
import com.example.intership_scrapproject_android.data.remote.response.BlogSearchItem
import kotlinx.coroutines.flow.Flow

interface BlogSearchRepository {

    fun getBlogSearchResult(query : String) : Flow<PagingData<BlogSearchItem>>
}