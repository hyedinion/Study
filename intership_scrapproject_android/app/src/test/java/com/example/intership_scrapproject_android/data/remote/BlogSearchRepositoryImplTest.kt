package com.example.intership_scrapproject_android.data.remote

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.intership_scrapproject_android.collectDataForTest
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.anyInt
import org.mockito.ArgumentMatchers.anyString
import org.mockito.BDDMockito.given
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.junit.Rule
import kotlinx.coroutines.flow.collect

//@RunWith(MockitoJUnitRunner::class)
//class BlogSearchRepositoryImplTest{
//
//    @Mock
//    lateinit var api : BlogSearchAPI
//
//    @get:Rule
//    var instantTaskExecutorRule = InstantTaskExecutorRule()
//
//    private lateinit var blogSearchRepository: BlogSearchRepositoryImpl
//    private val query = "query"
//
//    @Before
//    fun setup(){
//        blogSearchRepository = BlogSearchRepositoryImpl(api)
//    }
//

//    @ExperimentalCoroutinesApi
//    @InternalCoroutinesApi
//    @Test
//    fun `blog search paging source refresh - success`() = runBlocking {
//        //given
//        given(api.searchBlog(anyString(),anyString(),anyString(),anyInt(),anyInt()))
//            .willReturn(BlogSearchPagingSourceTest.blogSearchResponse)
//
//        //when
//        //lateinit var result : List<BlogSearchItem>
//        print("hello ")
//        val result = blogSearchRepository.getBlogSearchResult(query).collect{
//            assertThat(it.collectDataForTest()).isEqualTo(BlogSearchPagingSourceTest.blogSearchResponse.items)
//        }
//
//        //then
//        print("result")
//        assertThat(result).isEqualTo(BlogSearchPagingSourceTest.blogSearchResponse.items)
//    }
//
//}