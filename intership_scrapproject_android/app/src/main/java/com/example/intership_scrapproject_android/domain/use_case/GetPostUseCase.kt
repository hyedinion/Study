package com.example.intership_scrapproject_android.domain.use_case

import com.example.intership_scrapproject_android.core.util.OrderType
import com.example.intership_scrapproject_android.data.local.Post
import com.example.intership_scrapproject_android.data.local.PostRepository
import com.example.intership_scrapproject_android.data.local.Resource
import com.example.intership_scrapproject_android.data.local.Status
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetPostUseCase(
    private val postRepository : PostRepository
) {
    operator fun invoke(
        postOrder : OrderType = OrderType.SCRAP_DATE
    ) : Resource<Flow<List<Post>>>  {

        val posts = postRepository.getPost()

        if (posts.status==Status.ERROR){
            return posts
        }

        val resultPosts = posts.data?.map { posts ->
            when(postOrder){
                OrderType.SCRAP_DATE -> posts.sortedByDescending { it.scrapDate }
                OrderType.POST_DATE -> posts.sortedByDescending { it.postDate }
                OrderType.KEYWORD -> posts.sortedByDescending { it.keyword.lowercase() }
            }
        }

        return Resource.success(resultPosts)

    }
}