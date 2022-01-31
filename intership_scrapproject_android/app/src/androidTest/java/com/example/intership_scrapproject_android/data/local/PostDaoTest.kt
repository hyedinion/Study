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
    fun getPostByLinkTest(): Unit = runBlocking {
        //given
        val link = "link"
        val post = Post("title","description",link,"keyword","20220125","20220125","bloggerName",id=1)
        dao.insertPost(post)

        //when
        dao.getPostByLink(link)?.test {
            //then
            val postResultByLink = awaitItem()
            assertThat(postResultByLink).isEqualTo(post)
        }
    }

    @Test
    fun getNullByLinkDoesNotExistTest() : Unit = runBlocking {
        //given
        val link = "link"
        val post = Post("title","description",link,"keyword","20220125","20220125","bloggerName",id=1)
        dao.insertPost(post)

        //when
        val result = dao.getPostByLink("")?.test {
            val postResultByLink = awaitItem()
            if (postResultByLink!=null){
                assert(false)
            }
        }?:{
            //then
            assert(true)
        }

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