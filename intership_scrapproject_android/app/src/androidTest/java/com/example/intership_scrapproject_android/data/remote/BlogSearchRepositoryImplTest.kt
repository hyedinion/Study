package com.example.intership_scrapproject_android.data.remote

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.intership_scrapproject_android.BlogSearchResult
import com.example.intership_scrapproject_android.collectDataForAndroidTest
import com.example.intership_scrapproject_android.data.remote.response.BlogSearchItem
import com.example.intership_scrapproject_android.data.remote.response.BlogSearchResponse
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
import kotlinx.coroutines.flow.collect
import org.junit.Rule
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule

//@RunWith(AndroidJUnit4::class)
//class BlogSearchRepositoryImplAndroidTest{
//
//    @get:Rule
//    var rule: MockitoRule = MockitoJUnit.rule()
//
//    @Mock
//    lateinit var api : BlogSearchAPI
//
//    private lateinit var blogSearchRepository: BlogSearchRepositoryImpl
//    private val query = "query"
//
//    private val blogSearchResponse = BlogSearchResult.blogSearchResult
//
//    @Before
//    fun setup(){
//        blogSearchRepository = BlogSearchRepositoryImpl(api)
//    }
//

//    @ExperimentalCoroutinesApi
//    @InternalCoroutinesApi
//    @Test
//    fun blogSearchPagingSourceRefreshSuccess() = runBlocking {
//        //given
//        given(api.searchBlog(anyString(),anyString(),anyString(),anyInt(),anyInt()))
//            .willReturn(blogSearchResponse)
//
//        //when
//        //lateinit var result : List<BlogSearchItem>
//        print("hello ")
//        val result = blogSearchRepository.getBlogSearchResult(query).collect{
//            assertThat(it.collectDataForAndroidTest()).isEqualTo(blogSearchResponse.items)
//        }
//
//        //then
//        print("result")
//        assertThat(result).isEqualTo(blogSearchResponse.items)
//    }
//
//}