package com.example.intership_scrapproject_android.domain.use_case

import androidx.test.ext.junit.runners.AndroidJUnit4
import app.cash.turbine.test
import com.example.intership_scrapproject_android.data.local.InvalidPostException
import com.example.intership_scrapproject_android.data.local.Post
import com.example.intership_scrapproject_android.data.local.PostDao
import com.example.intership_scrapproject_android.data.local.PostDatabase
import com.google.common.truth.Truth.assertThat
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import javax.inject.Inject

@RunWith(AndroidJUnit4::class)
@HiltAndroidTest
class DeletePostUseCaseTest{

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var deletePostUseCase: DeletePostUseCase
    lateinit var dao: PostDao

    private val post = Post("title", "description", "link", "a", "20220125", "20220125", "bloggerName", id = 1)

    @Inject
    lateinit var postDatabase: PostDatabase

    @Before
    fun setup(): Unit = runBlocking {
        hiltRule.inject()
        dao = postDatabase.postDao
    }

    @Test
    fun deletePostUseCase_Success(): Unit = runBlocking {
        //given
        dao.insertPost(post)
        dao.getPost().test {
            val allPost = awaitItem()
            assertThat(allPost).contains(post)
        }

        //when
        deletePostUseCase(post)

        //then
        dao.getPost().test {
            val allPost = awaitItem()
            assertThat(allPost).doesNotContain(post)
        }
    }

    @Test
    fun deletePostUseCase_Fail_ZeroDelete(): Unit = runBlocking {
        //when
        val result = deletePostUseCase(post)

        //then
        assertThat(result.data).isEqualTo(0)
    }


}