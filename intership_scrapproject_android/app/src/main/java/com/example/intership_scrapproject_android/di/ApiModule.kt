package com.example.intership_scrapproject_android.di

import com.example.intership_scrapproject_android.data.remote.BlogSearchAPI
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApiModule {

    @Singleton
    @Provides
    fun provideSearchAPI() : BlogSearchAPI{
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://openapi.naver.com")
            .build()
            .create(BlogSearchAPI::class.java)
    }
}