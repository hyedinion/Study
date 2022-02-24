package com.example.intership_scrapproject_android

import com.example.intership_scrapproject_android.data.remote.response.BlogSearchItem
import com.example.intership_scrapproject_android.data.remote.response.BlogSearchResponse

object FakeBlogSearchResponse {
    val fakeBlogSearchResult = BlogSearchResponse(
        lastBuildDate= "lastBuildDate",
        total = 10,
        start = 1,
        display = 1,
        items=
        listOf(
            BlogSearchItem(
                title =  "title",
                link = "link",
                description = "description",
                bloggername = "bloggername",
                bloggerlink = "bloggerlink",
                postdate = "20220116"
            )
        )
    )
    val fakeLinkExistBlogSearchResult = BlogSearchResponse(
        lastBuildDate= "lastBuildDate",
        total = 10,
        start = 1,
        display = 1,
        items=
        listOf(
            BlogSearchItem(
                title =  "title",
                link = "link",
                description = "description",
                bloggername = "bloggername",
                bloggerlink = "bloggerlink",
                postdate = "20220116",
                linkExist = true,
            )
        )
    )
    val fakeZeroBlogSearchResult = BlogSearchResponse(
        lastBuildDate= "lastBuildDate",
        total = 10,
        start = 1,
        display = 1,
        items= listOf()
    )

}