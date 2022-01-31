package com.example.intership_scrapproject_android.data.local

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(indices = [Index(value = ["link"], unique = true)])
data class Post(
    val title : String,
    val description : String,
    val link : String,
    val keyword : String,
    val scrapDate : String,
    val postDate : String,
    val bloggerName : String,
    @PrimaryKey(autoGenerate = true)
    val id : Int? = null
)