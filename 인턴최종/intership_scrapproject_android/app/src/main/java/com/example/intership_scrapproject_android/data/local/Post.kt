package com.example.intership_scrapproject_android.data.local

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(indices = [Index(value = ["link"], unique = true)])
data class Post(
    var title : String,
    var description : String,
    val link : String,
    val keyword : String,
    var scrapDate : String,
    val postDate : String,
    val bloggerName : String,
    @PrimaryKey(autoGenerate = true)
    val id : Int? = null
) : Parcelable

class InvalidPostException(message : String) : Exception(message)