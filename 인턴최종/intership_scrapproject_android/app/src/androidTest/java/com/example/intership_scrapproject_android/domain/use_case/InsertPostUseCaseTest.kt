package com.example.intership_scrapproject_android.domain.use_case

import androidx.test.ext.junit.runners.AndroidJUnit4
import app.cash.turbine.test
import com.example.intership_scrapproject_android.data.local.*
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import javax.inject.Inject
import com.google.common.truth.Truth.assertThat


@RunWith(AndroidJUnit4::class)
@HiltAndroidTest
class InsertPostUseCaseTest{

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var insertPostUseCase: InsertPostUseCase
    lateinit var dao: PostDao

    @Inject
    lateinit var postDatabase: PostDatabase

    @Before
    fun setup() {
        hiltRule.inject()
        dao = postDatabase.postDao
    }

    @Test
    fun insertPost_Success() = runBlocking {
        //given
        val post = Post("title","description","link","keyword","20220125","20220125","bloggerName",id=1)

        //when
        insertPostUseCase(post)

        //then
        dao.getPost().test {
            val allPost = awaitItem()
            assertThat(allPost).contains(post)
        }
    }


    @Test(expected = InvalidPostException::class)
    fun insertPost_Fail_titleBlank(): Unit = runBlocking {
        //given
        val post = Post("","description","link","keyword","20220125","20220125","bloggerName",id=1)

        //when
        insertPostUseCase(post)
    }

    @Test(expected = InvalidPostException::class)
    fun insertPost_Fail_descriptionBlank(): Unit = runBlocking {
        //given
        val post = Post("title","","link","keyword","20220125","20220125","bloggerName",id=1)

        //when
        insertPostUseCase(post)
    }

}