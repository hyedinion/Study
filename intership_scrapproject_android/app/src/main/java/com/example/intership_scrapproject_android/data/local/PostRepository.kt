package com.example.intership_scrapproject_android.data.local

import kotlinx.coroutines.flow.Flow

interface PostRepository {

    fun getPost() : Flow<List<Post>>

    fun getPostByLink(link : String) : Flow<Post>?

    suspend fun insertPost(post: Post)

    suspend fun deletePost(post: Post)
}