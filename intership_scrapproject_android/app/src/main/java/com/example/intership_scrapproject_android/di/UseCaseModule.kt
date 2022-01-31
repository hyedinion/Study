package com.example.intership_scrapproject_android.di

import com.example.intership_scrapproject_android.data.local.PostRepository
import com.example.intership_scrapproject_android.data.remote.BlogSearchRepository
import com.example.intership_scrapproject_android.domain.use_case.BlogSearchUseCase
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
}