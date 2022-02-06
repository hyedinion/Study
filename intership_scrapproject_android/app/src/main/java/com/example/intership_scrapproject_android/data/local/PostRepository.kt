package com.example.intership_scrapproject_android.data.local

import kotlinx.coroutines.flow.Flow

interface PostRepository {

    fun getPost() : Resource<Flow<List<Post>>>

    suspend fun getPostByLink(link : String) : Resource<Post?>

    suspend fun insertPost(post: Post) : Resource<Unit>

    suspend fun deletePost(post: Post) : Resource<Int>
}