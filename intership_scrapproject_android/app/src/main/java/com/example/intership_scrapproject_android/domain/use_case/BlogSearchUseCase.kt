package com.example.intership_scrapproject_android.domain.use_case

import android.util.Log
import androidx.paging.PagingData
import androidx.paging.map
import com.example.intership_scrapproject_android.data.local.PostRepository
import com.example.intership_scrapproject_android.data.remote.BlogSearchRepository
import com.example.intership_scrapproject_android.data.remote.response.BlogSearchItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class BlogSearchUseCase(
    private val blogSearchRepository: BlogSearchRepository,
    private val postRepository : PostRepository
) {
    operator fun invoke(
        query : String
    ) : Flow<PagingData<BlogSearchItem>> {
        return blogSearchRepository.getBlogSearchResult(query).map { pagingData ->
            pagingData.map { blogSearchItem ->
                postRepository.getPostByLink(blogSearchItem.link)?.map {
                    blogSearchItem.linkExist = true
                }
                blogSearchItem
            }
        }
    }

}