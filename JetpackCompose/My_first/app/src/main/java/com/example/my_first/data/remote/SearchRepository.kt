package com.example.my_first.data.remote

import android.util.Log
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow

class SearchRepository (private val searchApi : SearchAPI) {

    fun getSearchResults(query : String) : Flow<PagingData<Item>> {
        Log.d("repository확인","repository")
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                maxSize = 1000,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { SearchPagingSource(searchApi, query) }
        ).flow
    }
}