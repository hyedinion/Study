package com.example.intership_scrapproject_android.data.remote

import androidx.paging.PagingSource
import com.example.intership_scrapproject_android.data.remote.response.BlogSearchItem
import com.example.intership_scrapproject_android.data.remote.response.BlogSearchResponse
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import okhttp3.MediaType
import okhttp3.ResponseBody
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.*
import org.mockito.BDDMockito.given
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.HttpException
import retrofit2.Response
import org.mockito.ArgumentMatchers.anyString


@RunWith(MockitoJUnitRunner::class)
class BlogSearchPagingSourceTest {

    @Mock
    lateinit var api : BlogSearchAPI

    lateinit var blogSearchPagingSource: BlogSearchPagingSource

    companion object{
        val blogSearchResponse = BlogSearchResponse(
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

        val nextBlogSearchResponse = BlogSearchResponse(
            lastBuildDate= "lastBuildDate",
            total = 10,
            start = 2,
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
    }

    @Before
    fun setup() {
        blogSearchPagingSource = BlogSearchPagingSource(api,"query")
    }

    @Test
    fun `blog search paging source load failure - http error`() = runBlocking {
        //given
        val error = HttpException(Response.error<Any>(404, ResponseBody.create(MediaType.parse("plain/text"),"content") ))
        given(api.searchBlog(anyString(),anyString(),anyString(), anyInt(),anyInt()))
            .willThrow(error)

        //when
        val expectResult = PagingSource.LoadResult.Error<Int,BlogSearchItem>(error)
        val blogSearchTestResult = blogSearchPagingSource.load(
            PagingSource.LoadParams.Refresh(
                key = 0,
                loadSize = 1,
                placeholdersEnabled = false
            )
        )

        //then
        assertThat(blogSearchTestResult).isEqualTo(expectResult)
    }


    @Test
    fun `blog search paging source refresh - success`() = runBlocking {
        //given
        given(api.searchBlog(anyString(),anyString(),anyString(), anyInt(),anyInt()))
            .willReturn(blogSearchResponse)

        //when
        val expectedResult = PagingSource.LoadResult.Page(
            data = blogSearchResponse.items,
            prevKey = null,
            nextKey = 1
        )
        val blogSearchTestResult = blogSearchPagingSource.load(
            PagingSource.LoadParams.Refresh(
                key = 0,
                loadSize = 1,
                placeholdersEnabled = false
            )
        )

        //then
        assertThat(blogSearchTestResult).isEqualTo(expectedResult)
    }

    @Test
    fun `blog search paging source append - success`() = runBlocking {
        //given
        given(api.searchBlog(anyString(),anyString(),anyString(), anyInt(),anyInt()))
            .willReturn(nextBlogSearchResponse)

        //when
        val expectedResult = PagingSource.LoadResult.Page(
            data = blogSearchResponse.items,
            prevKey = null,
            nextKey = 2
        )
        val blogSearchTestResult = blogSearchPagingSource.load(
            PagingSource.LoadParams.Append(
                key = 1,
                loadSize = 1,
                placeholdersEnabled = false
            )
        )

        //then
        assertThat(blogSearchTestResult).isEqualTo(expectedResult)
    }

}