package com.example.intership_scrapproject_android.domain.use_case

import androidx.paging.PagingData
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.intership_scrapproject_android.BlogSearchResult
import com.example.intership_scrapproject_android.collectDataForAndroidTest
import com.example.intership_scrapproject_android.data.local.Post
import com.example.intership_scrapproject_android.data.local.PostDao
import com.example.intership_scrapproject_android.data.local.PostDatabase
import com.example.intership_scrapproject_android.data.local.PostRepositoryImpl
import com.example.intership_scrapproject_android.data.remote.BlogSearchRepositoryImpl
import com.example.intership_scrapproject_android.data.remote.response.BlogSearchResponse
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.BDDMockito.given
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule

@RunWith(AndroidJUnit4::class)
class BlogSearchUseCaseAndroidTest{

    private lateinit var blogSearchUseCase: BlogSearchUseCase
    private val query = "query"

    @get:Rule
    var rule: MockitoRule = MockitoJUnit.rule()

    @Mock
    private lateinit var blogSearchRepository: BlogSearchRepositoryImpl

    private lateinit var database : PostDatabase
    private lateinit var dao : PostDao
    private lateinit var postRepository: PostRepositoryImpl

    private lateinit var blogSearchResponse : BlogSearchResponse

    @Before
    fun setup(){
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            PostDatabase::class.java
        ).allowMainThreadQueries().build()

        dao = database.postDao
        postRepository = PostRepositoryImpl(dao)

        blogSearchUseCase = BlogSearchUseCase(blogSearchRepository,postRepository)
        blogSearchResponse = BlogSearchResult.blogSearchResult
        val fakePagingData = PagingData.from(blogSearchResponse.items)
        given(blogSearchRepository.getBlogSearchResult(MockitoHelper.anyObject()))
            .willReturn(flow { emit(fakePagingData) })
    }

    @After
    fun teardown(){
        database.close()
    }

    @ExperimentalCoroutinesApi
    @Test
    fun blogSearchUseCaseTestSuccess(): Unit = runBlocking {

        //given
        val fakePagingData = PagingData.from(blogSearchResponse.items)
        given(blogSearchRepository.getBlogSearchResult(MockitoHelper.anyObject()))
            .willReturn(flow { emit(fakePagingData) })

        blogSearchUseCase(query).data?.collect{
            assertThat(it.collectDataForAndroidTest()).isEqualTo(blogSearchResponse.items)
        }
    }

    @ExperimentalCoroutinesApi
    @Test
    fun blogSearchUseCaseTestSuccessLinkNotExist(): Unit = runBlocking {

        //given
        val fakePagingData = PagingData.from(blogSearchResponse.items)
        given(blogSearchRepository.getBlogSearchResult(MockitoHelper.anyObject()))
            .willReturn(flow { emit(fakePagingData) })

        blogSearchUseCase(query).data?.collect{
            assertThat(it.collectDataForAndroidTest()[0].linkExist).isEqualTo(false)
        }
    }

    @ExperimentalCoroutinesApi
    @Test
    fun blogSearchUseCaseTestSuccessLinkExist(): Unit = runBlocking {

        val post = Post("title","description","link","keyword","scrapDate","postDate","bloggerName",1)
        postRepository.insertPost(post)

        //given
        val fakePagingData = PagingData.from(blogSearchResponse.items)
        given(blogSearchRepository.getBlogSearchResult(MockitoHelper.anyObject()))
            .willReturn(flow { emit(fakePagingData) })


        blogSearchUseCase(query).data?.collect{
            assertThat(it.collectDataForAndroidTest()[0].linkExist).isEqualTo(true)
        }

        postRepository.deletePost(post)


    }

    object MockitoHelper {
        fun <T> anyObject(): T {
            Mockito.any<T>()
            return uninitialized()
        }
        @Suppress("UNCHECKED_CAST")
        fun <T> uninitialized(): T =  null as T
    }

}
