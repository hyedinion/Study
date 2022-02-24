package com.example.intership_scrapproject_android.domain.use_case

import androidx.test.ext.junit.runners.AndroidJUnit4
import app.cash.turbine.test
import com.example.intership_scrapproject_android.data.local.Post
import com.example.intership_scrapproject_android.data.local.PostDao
import com.example.intership_scrapproject_android.data.local.PostDatabase
import com.example.intership_scrapproject_android.data.local.Status
import com.example.intership_scrapproject_android.util.OrderType
import com.google.common.truth.Truth.assertThat
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import javax.inject.Inject

@RunWith(AndroidJUnit4::class)
@HiltAndroidTest
class GetPostUseCaseTest{

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var getPostUseCase: GetPostUseCase
    lateinit var dao: PostDao

    @Inject
    lateinit var postDatabase: PostDatabase

    private val post1 = Post("title","description","link","a","20220125","20220125","bloggerName",id=1)
    private val post2 = Post("title","description","link2","b","20220126","20220126","bloggerName",id=2)


    @Before
    fun setup(): Unit = runBlocking {
        hiltRule.inject()
        dao = postDatabase.postDao
        dao.insertPost(post1)
        dao.insertPost(post2)
    }

    @After
    fun teardown(): Unit = runBlocking{
        dao.deletePost(post1)
        dao.deletePost(post2)
    }

    @InternalCoroutinesApi
    @Test
    fun getPost_PostDate_Success(): Unit = runBlocking {
        //when
        val postDateResult = getPostUseCase(OrderType.POST_DATE)

        //then
        assertThat(postDateResult.status).isEqualTo(Status.SUCCESS)
        postDateResult.data?.test{
            val allPost = awaitItem()
            assertThat(allPost).isEqualTo(listOf(post2,post1))
        }
    }

    @InternalCoroutinesApi
    @Test
    fun getPost_ScrapDate_Success(): Unit = runBlocking {
        //when
        val postDateResult = getPostUseCase(OrderType.SCRAP_DATE)

        //then
        assertThat(postDateResult.status).isEqualTo(Status.SUCCESS)
        postDateResult.data?.test{
            val allPost = awaitItem()
            assertThat(allPost).isEqualTo(listOf(post2,post1))
        }
    }

    @InternalCoroutinesApi
    @Test
    fun getPost_Keyword_Success(): Unit = runBlocking {
        //when
        val postDateResult = getPostUseCase(OrderType.KEYWORD)

        //then
        assertThat(postDateResult.status).isEqualTo(Status.SUCCESS)
        postDateResult.data?.test{
            val allPost = awaitItem()
            assertThat(allPost).isEqualTo(listOf(post1,post2))
        }
    }

}