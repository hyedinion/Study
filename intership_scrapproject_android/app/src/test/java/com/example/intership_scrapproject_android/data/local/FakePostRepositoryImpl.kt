package com.example.intership_scrapproject_android.data.local

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakePostRepositoryImpl : PostRepository {

    private val posts = mutableListOf<Post>()

    override fun getPost(): Flow<List<Post>> {
        return flow { emit(posts) }
    }

    override fun getPostByLink(link: String): Flow<Post>? {
        return if (posts.find { it.link == link } == null){
            null
        } else{
            flow { emit(posts.find { it.link == link }!!) }
        }
    }

    override suspend fun insertPost(post: Post) {
        posts.add(post)
    }

    override suspend fun deletePost(post: Post) {
        posts.remove(post)
    }
}