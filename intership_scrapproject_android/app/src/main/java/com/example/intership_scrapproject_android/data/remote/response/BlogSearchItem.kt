package com.example.intership_scrapproject_android.data.remote.response

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class BlogSearchItem(
    val bloggerlink: String,
    val bloggername: String,
    var description: String,
    val link: String,
    var postdate: String,
    var title: String,
    var linkExist : Boolean = false
) : Parcelable