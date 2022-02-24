package com.example.intership_scrapproject_android.domain.use_case

import androidx.paging.PagingData
import androidx.paging.map
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.intership_scrapproject_android.FakeBlogSearchResponse
import com.example.intership_scrapproject_android.MockitoHelper
import com.example.intership_scrapproject_android.data.local.Post
import com.example.intership_scrapproject_android.data.local.PostDao
import com.example.intership_scrapproject_android.data.local.PostDatabase
import com.example.intership_scrapproject_android.data.local.PostRepositoryImpl
import com.example.intership_scrapproject_android.data.remote.BlogSearchRepositoryImpl
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
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule

@RunWith(AndroidJUnit4::class)
class BlogSearchUseCaseAndroidTest{

    @get:Rule
    var rule: MockitoRule = MockitoJUnit.rule()

    @Mock
    private lateinit var blogSearchRepository: BlogSearchRepositoryImpl

    private lateinit var blogSearchUseCase: BlogSearchUseCase
    private val query = "query"

    private lateinit var database : PostDatabase
    private lateinit var dao : PostDao
    private lateinit var postRepository: PostRepositoryImpl

    private var blogSearchResponse  = FakeBlogSearchResponse.fakeBlogSearchResult

    @Before
    fun setup(){
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            PostDatabase::class.java
        ).allowMainThreadQueries().build()

        dao = database.postDao
        postRepository = PostRepositoryImpl(dao)
        blogSearchUseCase = BlogSearchUseCase(blogSearchRepository,postRepository)

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

        blogSearchUseCase(query).data?.collect{ pagingData ->
            pagingData.map {
                //then
                assertThat(it.linkExist).isEqualTo(true)
            }
        }
    }

    @ExperimentalCoroutinesApi
    @Test
    fun blogSearchUseCaseTestSuccessLinkNotExist(): Unit = runBlocking {

        //given
        val fakePagingData = PagingData.from(blogSearchResponse.items)
        given(blogSearchRepository.getBlogSearchResult(MockitoHelper.anyObject()))
            .willReturn(flow { emit(fakePagingData) })

        //when
        blogSearchUseCase(query).data?.collect{ pagingData ->
            pagingData.map {
                //then
                assertThat(it.linkExist).isEqualTo(false)
            }
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


        blogSearchUseCase(query).data?.collect{ pagingData ->
            pagingData.map {
                assertThat(it.linkExist).isEqualTo(true)
            }
        }
        postRepository.deletePost(post)
    }

    @Test
    fun blogSearchUseCaseTestHtmlToStringSuccessWithNoTag() = runBlocking {
        //given
        val htmlStr = "hi"

        //when
        val result = blogSearchUseCase.htmlToString(htmlStr)

        //then
        assertThat(result).isEqualTo("hi")
    }

    @Test
    fun blogSearchUseCaseTestHtmlToStringSuccessWithPTag() = runBlocking {
        //given
        val htmlStr = "<p>hi</p>"

        //when
        val result = blogSearchUseCase.htmlToString(htmlStr)

        //then
        assertThat(result).isEqualTo("hi\n")
    }

}
