package com.example.my_first.data.remote

import com.example.my_first.BuildConfig
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface SearchAPI {

    @GET("/v1/search/blog")
    suspend fun searchBlog(
        @Header("X-Naver-Client-Id") apiId :String = BuildConfig.API_ID,
        @Header("X-Naver-Client-Secret") apiSecret : String = BuildConfig.API_SECRET,
        @Query("query") query : String,
        @Query("display") display: Int, //출력 건수,10, 최대 100
        @Query("start") start: Int //시작 위치,1, 최대 1000
    ): SearchResponse

}