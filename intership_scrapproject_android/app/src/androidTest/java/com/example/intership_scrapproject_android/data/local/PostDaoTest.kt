package com.example.intership_scrapproject_android.data.local

import android.database.sqlite.SQLiteException
import androidx.paging.PagingSource
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import app.cash.turbine.test
import com.example.intership_scrapproject_android.data.remote.response.BlogSearchItem
import com.example.intership_scrapproject_android.di.ApiModule
import com.example.intership_scrapproject_android.di.DatabaseModule
import com.example.intership_scrapproject_android.di.RepositoryModule
import com.example.intership_scrapproject_android.di.UseCaseModule
import com.google.common.truth.Truth.assertThat
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import kotlinx.coroutines.runBlocking
import okhttp3.MediaType
import okhttp3.ResponseBody
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers
import org.mockito.BDDMockito
import retrofit2.HttpException
import retrofit2.Response


@RunWith(AndroidJUnit4::class)
class PostDaoTest {

    private lateinit var database : PostDatabase
    private lateinit var dao : PostDao

    @Before
    fun setup(){
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            PostDatabase::class.java
        ).allowMainThreadQueries().build()
        dao = database.postDao
    }

    @After
    fun teardown(){
        database.close()
    }

    @Test
    fun insertPostTest() = runBlocking{
        //given
        val post = Post("title","description","link","keyword","20220125","20220125","bloggerName",id=1)

        //when
        dao.insertPost(post)

        //then
        dao.getPost().test {
            val allPost = awaitItem()
            assertThat(allPost).contains(post)
        }
    }

    @Test
    fun deletePostTest() = runBlocking {
        //given
        val post = Post("title","description","link","keyword","20220125","20220125","bloggerName",id=1)
        dao.insertPost(post)

        //when
        dao.deletePost(post)

        //then
        dao.getPost().test {
            val allPost = awaitItem()
            assertThat(allPost).doesNotContain(post)
        }

    }

    @Test
    fun deletePostTest_Fail(): Unit = runBlocking {

        //given
        val post = Post("","","","","","","",id=30)

        //when
        val result = dao.deletePost(post)
        assertThat(result).isEqualTo(0)

    }

    @Test
    fun getPostByLinkTest(): Unit = runBlocking {
        //given
        val link = "link"
        val post = Post("title","description",link,"keyword","20220125","20220125","bloggerName",id=1)
        dao.insertPost(post)

        //when
        val postResultByLink = dao.getPostByLink(link)

        //then
        assertThat(postResultByLink).isEqualTo(post)
    }

    @Test
    fun getNullByLinkDoesNotExistTest() : Unit = runBlocking {
        //given
        val link = "link"
        val post = Post("title","description",link,"keyword","20220125","20220125","bloggerName",id=1)
        dao.insertPost(post)

        //when
        val result = dao.getPostByLink("")

        //then
        assertThat(result).isNull()

    }

    @Test
    fun postIdAutoGenerateTest() = runBlocking{
        //given
        val post = Post("title","description","link","keyword","20220125","20220125","bloggerName")

        //when
        dao.insertPost(post)

        //then
        dao.getPost().test {
            val allPost = awaitItem()
            assertThat(allPost[0].id).isEqualTo(1)
        }
    }


}