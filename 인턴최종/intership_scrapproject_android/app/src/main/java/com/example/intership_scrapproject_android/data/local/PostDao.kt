package com.example.intership_scrapproject_android.data.local

import android.database.sqlite.SQLiteException
import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface PostDao {

    @Query("SELECT * FROM post")
    @Throws(SQLiteException::class)
    fun getPost() : Flow<List<Post>>

    @Query("SELECT * FROM post WHERE link = :link")//:는 파라미터로 넘겨준 변수라는 것을 알려줌
    @Throws(SQLiteException::class)
    suspend fun getPostByLink(link : String) : Post?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    @Throws(SQLiteException::class)
    suspend fun insertPost(post : Post)

    @Delete
    @Throws(SQLiteException::class)
    suspend fun deletePost(post : Post) : Int

}