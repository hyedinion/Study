package com.example.intership_scrapproject_android.data.local

import kotlinx.coroutines.flow.Flow

class PostRepositoryImpl(
    private val dao: PostDao
) : PostRepository {
    override fun getPost(): Flow<List<Post>> {
        return dao.getPost()
    }

    override fun getPostByLink(link: String): Flow<Post>? {
        return dao.getPostByLink(link)
    }

    override suspend fun insertPost(post: Post) {
        dao.insertPost(post)
    }

    override suspend fun deletePost(post: Post) {
        dao.deletePost(post)
    }
}