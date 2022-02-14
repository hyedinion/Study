package com.example.intership_scrapproject_android.domain.use_case

import android.util.Log
import com.example.intership_scrapproject_android.data.local.Post
import com.example.intership_scrapproject_android.data.local.PostRepository
import com.example.intership_scrapproject_android.data.local.Resource
import com.example.intership_scrapproject_android.data.local.Status
import kotlinx.coroutines.flow.map

class PostSearchUseCase(
    private val postRepository : PostRepository
) {
    operator fun invoke(
        query : String
    ) : Resource<List<Post>> {

        val postRepositoryResult = postRepository.getPost()
        val newPosts : MutableList<Post> = mutableListOf()

        if (postRepositoryResult.status== Status.ERROR){
            return Resource.error(msg = postRepositoryResult.message ?:"unknown error", null)
        }

        postRepositoryResult.data?.map { posts ->
            posts.forEach { post ->
                Log.d("searchPostUseCase","title : ${post.title}, query : $query")
                if (post.title.contains(query,ignoreCase = true)){
                    newPosts.add(post)
                    Log.d("searchPostUseCase","add")
                }
                else if (post.description.contains(query,ignoreCase = true)){
                    newPosts.add(post)
                }
            }
        }

        Log.d("searchPostUseCase","finish")
        return Resource.success(newPosts)

    }
}