package com.example.intership_scrapproject_android.data.remote

import android.util.Log
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.intership_scrapproject_android.data.remote.response.BlogSearchItem
import kotlinx.coroutines.flow.Flow

class BlogSearchRepositoryImpl (
    private val blogSearchAPI: BlogSearchAPI
) : BlogSearchRepository{
    override fun getBlogSearchResult(query: String): Flow<PagingData<BlogSearchItem>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                maxSize = 1000,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { BlogSearchPagingSource(blogSearchAPI, query) }
        ).flow
    }
}