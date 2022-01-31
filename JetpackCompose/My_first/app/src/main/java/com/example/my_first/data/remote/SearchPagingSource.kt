package com.example.my_first.data.remote

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import retrofit2.HttpException
import java.io.IOException

private const val UNSPLASH_STARTING_PAGE_INDEX = 1

class SearchPagingSource (
    private val searchApi : SearchAPI,
    private val query: String
) : PagingSource<Int, Item>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Item> {
        val position = params.key?: UNSPLASH_STARTING_PAGE_INDEX

        return try {
            val response =
                searchApi.searchBlog(query = query, display = params.loadSize, start = (position-1)*params.loadSize+1)
            val items = response.items
            Log.d("paging확인","total : ${response.total} position : ${position}")

            LoadResult.Page(
                data = items,
                //prevKey = if (position == UNSPLASH_STARTING_PAGE_INDEX) null else position-1,
                prevKey = null,
                //nextKey = if (items.isEmpty()) null else position+1
                nextKey = if (position*params.loadSize>response.total) null else position +1
            )
        }catch (exception : IOException){ //internet connection 문제
            Log.d("error","ioerror")
            LoadResult.Error(exception)

        }catch (exception : HttpException){ //server 문제
            Log.d("error","httperror")
            LoadResult.Error(exception)
        }

    }

    override fun getRefreshKey(state: PagingState<Int, Item>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }


}