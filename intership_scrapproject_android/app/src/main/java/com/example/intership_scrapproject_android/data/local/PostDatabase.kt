package com.example.intership_scrapproject_android.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [Post::class],
    version = 1
)
abstract class PostDatabase : RoomDatabase(){
    abstract val postDao : PostDao
}