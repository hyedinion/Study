package com.example.intership_scrapproject_android.data.local

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import app.cash.turbine.test
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class PostRepositoryImplTest {

    private lateinit var database : PostDatabase
    private lateinit var dao : PostDao
    private lateinit var repository: PostRepositoryImpl

    @Before
    fun setup(){
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            PostDatabase::class.java
        ).allowMainThreadQueries().build()
        dao = database.postDao
        repository = PostRepositoryImpl(dao)
    }

    @After
    fun teardown(){
        database.close()
    }

    @Test
    fun insertPostTest(): Unit = runBlocking{
        //given
        val post = Post("title","description","link","keyword","20220125","20220125","bloggerName",id=1)

        //when
        repository.insertPost(post)

        //then
        repository.getPost().data?.test {
            val allPost = awaitItem()
            assertThat(allPost).contains(post)
        }
    }

    @Test
    fun deletePostTest(): Unit = runBlocking {
        //given
        val post = Post("title","description","link","keyword","20220125","20220125","bloggerName",id=1)
        repository.insertPost(post)

        //when
        repository.deletePost(post)

        //then
        repository.getPost().data?.test {
            val allPost = awaitItem()
            assertThat(allPost).doesNotContain(post)
        }

    }

    @Test
    fun deletePostTest_Fail(): Unit = runBlocking {
        //given
        val post = Post("title","description","link","keyword","20220125","20220125","bloggerName",id=1)

        //when
        val result = repository.deletePost(post)

        //then
        assertThat(result.data).isEqualTo(0)
    }

    @Test
    fun getPostByLinkTest(): Unit = runBlocking {
        //given
        val link = "link"
        val post = Post("title","description",link,"keyword","20220125","20220125","bloggerName",id=1)
        repository.insertPost(post)

        //when
        val postResultByLink = repository.getPostByLink(link)

        //then
        assertThat(postResultByLink.data).isEqualTo(post)
    }

    @Test
    fun getNullByLinkDoesNotExistTest() : Unit = runBlocking {
        //given
        val link = "link"
        val post = Post("title","description",link,"keyword","20220125","20220125","bloggerName",id=1)
        repository.insertPost(post)

        //when
        val postResultByLink = repository.getPostByLink(" ")

        //then
        assertThat(postResultByLink.data).isNull()

    }


}