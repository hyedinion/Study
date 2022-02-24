package com.example.intership_scrapproject_android.presentation.webview

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.navigation.compose.rememberNavController
import com.example.intership_scrapproject_android.data.local.Post
import com.example.intership_scrapproject_android.data.local.PostRepository
import com.example.intership_scrapproject_android.presentation.main.MainActivity
import com.example.intership_scrapproject_android.presentation.main.ScrapNavHost
import com.example.intership_scrapproject_android.ui.theme.IntershipScrapProjectAndroidTheme
import com.example.intership_scrapproject_android.util.TestTags
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import javax.inject.Inject

@RunWith(Parameterized::class)
@HiltAndroidTest
class PostWebViewScreenTest {

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeRule = createAndroidComposeRule<MainActivity>()

    @Inject
    lateinit var repository: PostRepository

    companion object {
        private const val numberOfTests = 1

        @JvmStatic
        @Parameterized.Parameters
        fun data(): Array<Array<Any?>> = Array(numberOfTests) { arrayOfNulls<Any?>(0) }
    }

    @ExperimentalFoundationApi
    @ExperimentalAnimationApi
    @ExperimentalComposeUiApi
    @Before
    fun setup(): Unit = runBlocking {
        hiltRule.inject()
        composeRule.setContent {
            IntershipScrapProjectAndroidTheme {
                val navController = rememberNavController()
                ScrapNavHost(navController = navController)
            }
        }
    }

    @Test
    fun postWebViewScreenExist(): Unit = runBlocking{
        //given
        val post = Post("title", "description", "link", "a", "20220125", "20220125", "bloggerName", id = 1)
        repository.insertPost(post)
        composeRule.onNodeWithTag(TestTags.postBottomButton).performClick()
        composeRule.onNodeWithTag(TestTags.postItem).performClick()

        //when
        composeRule.onNodeWithTag(TestTags.postLinkButton).performClick()

        //then
        composeRule.onNodeWithTag(TestTags.postWebView).assertExists()
        repository.deletePost(post)
    }

    @Test
    fun postWebViewScreenBackPress(): Unit = runBlocking{
        //given
        val post = Post("title", "description", "link", "a", "20220125", "20220125", "bloggerName", id = 1)
        repository.insertPost(post)
        composeRule.onNodeWithTag(TestTags.postBottomButton).performClick()
        composeRule.onNodeWithTag(TestTags.postItem).performClick()

        //when
        composeRule.onNodeWithTag(TestTags.postLinkButton).performClick()
        composeRule.onNodeWithTag(TestTags.postWebViewBackButton).performClick()

        //then
        composeRule.onNodeWithTag(TestTags.postDetailItem).assertExists()
        repository.deletePost(post)
    }

    @Test
    fun postWebViewScreen_Fail_LinkNull(): Unit = runBlocking{
        //given
        val nullLinkPost = Post("title", "description", "", "a", "20220125", "20220125", "bloggerName", id = 1)
        repository.insertPost(nullLinkPost)
        composeRule.onNodeWithTag(TestTags.postBottomButton).performClick()
        composeRule.onNodeWithTag(TestTags.postItem).performClick()

        //when
        composeRule.onNodeWithTag(TestTags.postLinkButton).performClick()

        //then
        composeRule.onNodeWithTag(TestTags.postDetailItem).assertExists()
    }



}