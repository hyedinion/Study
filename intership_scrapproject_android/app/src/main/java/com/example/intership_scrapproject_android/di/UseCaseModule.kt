package com.example.intership_scrapproject_android.di

import com.example.intership_scrapproject_android.data.local.PostRepository
import com.example.intership_scrapproject_android.data.remote.BlogSearchRepository
import com.example.intership_scrapproject_android.domain.use_case.BlogSearchUseCase
import com.example.intership_scrapproject_android.domain.use_case.GetPostUseCase
import com.example.intership_scrapproject_android.domain.use_case.InsertPostUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {

    @Provides
    @Singleton
    fun provideBlogSearchUseCase(
        blogSearchRepository: BlogSearchRepository,
        postRepository : PostRepository
    ): BlogSearchUseCase{
        return BlogSearchUseCase(blogSearchRepository, postRepository)
    }

    @Provides
    @Singleton
    fun provideBlogScrapUseCase(
        postRepository : PostRepository
    ): InsertPostUseCase{
        return InsertPostUseCase(postRepository)
    }

    @Provides
    @Singleton
    fun provideGetPostUseCase(
        postRepository : PostRepository
    ): GetPostUseCase{
        return GetPostUseCase(postRepository)
    }
}