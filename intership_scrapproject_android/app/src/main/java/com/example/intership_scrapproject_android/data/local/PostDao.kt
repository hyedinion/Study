package com.example.intership_scrapproject_android.data.local

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface PostDao {

    @Query("SELECT * FROM post")
    fun getPost() : Flow<List<Post>>

    @Query("SELECT * FROM post WHERE link = :link")//:는 파라미터로 넘겨준 변수라는 것을 알려줌
    fun getPostByLink(link : String) : Flow<Post>?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPost(post : Post)

    @Delete
    suspend fun deletePost(post : Post)

}