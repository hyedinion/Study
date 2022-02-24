package com.example.intership_scrapproject_android.di

import com.example.intership_scrapproject_android.data.local.PostDatabase
import com.example.intership_scrapproject_android.data.local.PostRepository
import com.example.intership_scrapproject_android.data.local.PostRepositoryImpl
import com.example.intership_scrapproject_android.data.remote.BlogSearchAPI
import com.example.intership_scrapproject_android.data.remote.BlogSearchRepository
import com.example.intership_scrapproject_android.data.remote.BlogSearchRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    @Singleton
    fun provideScrapRepository(db : PostDatabase) : PostRepository{
        return PostRepositoryImpl(db.postDao)
    }

    @Provides
    @Singleton
    fun provideSearchRepository(api : BlogSearchAPI) : BlogSearchRepository{
        return BlogSearchRepositoryImpl(api)
    }
}