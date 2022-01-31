package com.example.intership_scrapproject_android.data.remote.response

data class BlogSearchResponse(
    val display: Int,
    val items: List<BlogSearchItem>,
    val lastBuildDate: String,
    val start: Int,
    val total: Int
)