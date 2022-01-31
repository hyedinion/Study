package com.example.intership_scrapproject_android.data.remote.response

data class BlogSearchItem(
    val bloggerlink: String,
    val bloggername: String,
    val description: String,
    val link: String,
    val postdate: String,
    val title: String,
    var linkExist : Boolean = false
)