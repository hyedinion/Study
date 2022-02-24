package com.example.intership_scrapproject_android.domain.use_case

import com.example.intership_scrapproject_android.data.local.Post
import com.example.intership_scrapproject_android.data.local.PostRepository
import com.example.intership_scrapproject_android.data.local.Resource
import com.example.intership_scrapproject_android.data.local.Status
import com.example.intership_scrapproject_android.util.OrderType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetPostUseCase(
    private val postRepository : PostRepository
) {
    operator fun invoke(
        postOrder : OrderType
    ) : Resource<Flow<List<Post>>>  {

        val postRepositoryResult = postRepository.getPost()

        if (postRepositoryResult.status==Status.ERROR){
            return postRepositoryResult
        }

        val resultPosts = postRepositoryResult.data?.map { posts ->
            when(postOrder){
                OrderType.SCRAP_DATE -> posts.sortedByDescending { it.scrapDate }
                OrderType.POST_DATE -> posts.sortedByDescending { it.postDate }
                OrderType.KEYWORD -> posts.sortedBy { it.keyword.lowercase() }
            }
        }

        return Resource.success(resultPosts)

    }
}