package com.example.my_first.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Scrap(
    val title : String,
    val discription : String,
    val link : String,
    val keyword : String,
    val scrapDate : Long,
    val postDate : Long,
    @PrimaryKey(autoGenerate = true)
    val id : Int? = null
)
