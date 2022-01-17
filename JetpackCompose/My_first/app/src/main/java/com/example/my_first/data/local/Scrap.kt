package com.example.my_first.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "scrap_item")
data class Scrap (
    val title : String,
    val detail : String,
    val url : String,

    @PrimaryKey(autoGenerate = true)
    val id : Int? = null
)