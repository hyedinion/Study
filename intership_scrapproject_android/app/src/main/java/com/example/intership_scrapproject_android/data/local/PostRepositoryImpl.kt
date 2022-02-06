package com.example.intership_scrapproject_android.data.local

import android.database.sqlite.SQLiteException
import kotlinx.coroutines.flow.Flow

class PostRepositoryImpl(
    private val dao: PostDao
) : PostRepository {
    override fun getPost(): Resource<Flow<List<Post>>> {
        return try {
            dao.getPost().let {
                return@let Resource.success(it)
            }
        }catch (e : SQLiteException){
            Resource.error("Database getPost failed", null)
        }

    }

    override suspend fun getPostByLink(link: String): Resource<Post?> {
        return try {
            dao.getPostByLink(link).let {
                return@let Resource.success(it)
            }
        }catch (e : SQLiteException){
            Resource.error("Database getPostByLink failed", null)
        }
    }

    override suspend fun insertPost(post: Post) : Resource<Unit> {
        return try {
            dao.insertPost(post).let {
                return@let Resource.success(it)
            }
        }catch (e : SQLiteException){
            Resource.error("Database insertPost failed", null)
        }
    }

    override suspend fun deletePost(post: Post) : Resource<Int> {
        return try {
            dao.deletePost(post).let {
                return@let Resource.success(it)
            }
        }catch (e : SQLiteException){
            Resource.error("Database deletePost failed", null)
        }
    }
}