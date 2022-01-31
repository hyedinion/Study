package com.example.intership_scrapproject_android.domain.use_case

import androidx.paging.DifferCallback
import androidx.paging.NullPaddedList
import androidx.paging.PagingData
import androidx.paging.PagingDataDiffer
import app.cash.turbine.Event
import app.cash.turbine.test
import com.example.intership_scrapproject_android.collectDataForTest
import com.example.intership_scrapproject_android.data.local.FakePostRepositoryImpl
import com.example.intership_scrapproject_android.data.remote.BlogSearchPagingSourceTest
import com.example.intership_scrapproject_android.data.remote.BlogSearchRepositoryImpl
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.BDDMockito.given
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class BlogSearchUseCaseTest{

    private lateinit var blogSearchUseCase: BlogSearchUseCase
    private val query = "query"

    @Mock
    private lateinit var blogSearchRepository: BlogSearchRepositoryImpl

    private lateinit var fakePostRepository : FakePostRepositoryImpl

    @Before
    fun setup(){
        fakePostRepository = FakePostRepositoryImpl()
        blogSearchUseCase = BlogSearchUseCase(blogSearchRepository,fakePostRepository)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `blog search useCase test - success - link not exist`(): Unit = runBlocking {

        //given
        val fakePagingData = PagingData.from(BlogSearchPagingSourceTest.blogSearchResponse.items)
        given(blogSearchRepository.getBlogSearchResult(MockitoHelper.anyObject()))
            .willReturn(flow { emit(fakePagingData) })

        blogSearchUseCase(query).test {
            val event = awaitEvent()
            print(event)
            val result = awaitItem().collectDataForTest()
            assertThat(result).isEqualTo(BlogSearchPagingSourceTest.blogSearchResponse.items)

        }
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
