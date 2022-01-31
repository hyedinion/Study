package com.example.intership_scrapproject_android.data.remote

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.intership_scrapproject_android.data.remote.response.BlogSearchItem
import retrofit2.HttpException
import java.io.IOException

private const val UNSPLASH_STARTING_PAGE_INDEX = 1

class BlogSearchPagingSource (
    private val blogSearchApi : BlogSearchAPI,
    private val query: String
) : PagingSource<Int, BlogSearchItem>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, BlogSearchItem> {
        val position = params.key?: UNSPLASH_STARTING_PAGE_INDEX

        return try {
            val response =
                blogSearchApi.searchBlog(query = query, display = params.loadSize, start = (position-1)*params.loadSize+1)
            val items = response.items

            LoadResult.Page(
                data = items,
                prevKey = null,
                nextKey = if (position*params.loadSize>response.total) null else position +1
            )
        }catch (exception : IOException){ //internet connection 문제
            LoadResult.Error(exception)

        }catch (exception : HttpException){ //server 문제
            LoadResult.Error(exception)
        }

    }

    override fun getRefreshKey(state: PagingState<Int, BlogSearchItem>): Int? {
        return null
    }

}