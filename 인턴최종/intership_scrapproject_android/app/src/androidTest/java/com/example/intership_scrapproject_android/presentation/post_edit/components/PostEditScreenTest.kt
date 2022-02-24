package com.example.intership_scrapproject_android.presentation.post_edit.components

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.navigation.compose.rememberNavController
import app.cash.turbine.test
import com.example.intership_scrapproject_android.data.local.Post
import com.example.intership_scrapproject_android.data.local.PostRepository
import com.example.intership_scrapproject_android.presentation.main.MainActivity
import com.example.intership_scrapproject_android.presentation.main.ScrapNavHost
import com.example.intership_scrapproject_android.ui.theme.IntershipScrapProjectAndroidTheme
import com.example.intership_scrapproject_android.util.TestTags
import com.google.common.truth.Truth.assertThat
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
class PostEditScreenTest {

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeRule = createAndroidComposeRule<MainActivity>()

    @Inject
    lateinit var repository: PostRepository
    private val post = Post("title", "description", "link", "a", "20220125", "20220125", "bloggerName", id = 1)

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
        repository.insertPost(post)
    }

    @Test
    fun postEditScreenExist(): Unit = runBlocking{
        //given
        composeRule.onNodeWithTag(TestTags.postBottomButton).performClick()
        composeRule.onNodeWithTag(TestTags.postItem).performClick()

        //when
        composeRule.onNodeWithTag(TestTags.postEditButton).performClick()

        //then
        composeRule.onNodeWithTag(TestTags.postEditItem).assertExists()
    }

    @Test
    fun postEditScreenBackPress(): Unit = runBlocking{
        //given
        composeRule.onNodeWithTag(TestTags.postBottomButton).performClick()
        composeRule.onNodeWithTag(TestTags.postItem).performClick()

        //when
        composeRule.onNodeWithTag(TestTags.postEditButton).performClick()
        composeRule.onNodeWithTag(TestTags.postEditBackButton).performClick()

        //then
        composeRule.onNodeWithTag(TestTags.postDetailItem).assertExists()
    }

    @Test
    fun postEditScreenItemTextCheck(): Unit = runBlocking {
        //given
        composeRule.onNodeWithTag(TestTags.postBottomButton).performClick()
        composeRule.onNodeWithTag(TestTags.postItem).performClick()

        //when
        composeRule.onNodeWithTag(TestTags.postEditButton).performClick()

        //then
        composeRule.onNodeWithTag(TestTags.postEditTitleTextField).assertTextEquals(post.title)

    }

    @Test
    fun postEditScreenSaveButton_NotClickable(): Unit = runBlocking {
        //given
        composeRule.onNodeWithTag(TestTags.postBottomButton).performClick()
        composeRule.onNodeWithTag(TestTags.postItem).performClick()

        //when
        composeRule.onNodeWithTag(TestTags.postEditButton).performClick()

        //then
        composeRule.onNodeWithTag(TestTags.postEditSaveButton).assertIsNotEnabled()
    }

    @Test
    fun postEditScreenSaveButton_clickable_withTitle(): Unit = runBlocking {
        //given
        composeRule.onNodeWithTag(TestTags.postBottomButton).performClick()
        composeRule.onNodeWithTag(TestTags.postItem).performClick()

        //when
        composeRule.onNodeWithTag(TestTags.postEditButton).performClick()

        //then
        composeRule.onNodeWithTag(TestTags.postEditItem).assertExists()
        val testText = "hi"
        composeRule.onNodeWithTag(TestTags.postEditTitleTextField).performTextInput(testText)
        composeRule.onNodeWithTag(TestTags.postEditTitleTextField).assertTextContains(testText,true)
        composeRule.onNodeWithTag(TestTags.postEditSaveButton).assertIsEnabled()
    }

    @Test
    fun postEditScreenSaveButton_clickable_withDescription(): Unit = runBlocking {
        //given
        composeRule.onNodeWithTag(TestTags.postBottomButton).performClick()
        composeRule.onNodeWithTag(TestTags.postItem).performClick()

        //when
        composeRule.onNodeWithTag(TestTags.postEditButton).performClick()

        //then
        composeRule.onNodeWithTag(TestTags.postEditItem).assertExists()
        val testText = "hi"
        composeRule.onNodeWithTag(TestTags.postEditDescriptionTextField).performTextInput(testText)
        composeRule.onNodeWithTag(TestTags.postEditDescriptionTextField).assertTextContains(testText,true)
        composeRule.onNodeWithTag(TestTags.postEditSaveButton).assertIsEnabled()
    }

    @Test
    fun postEditScreenSaveSuccess(): Unit = runBlocking {
        //given
        composeRule.onNodeWithTag(TestTags.postBottomButton).performClick()
        composeRule.onNodeWithTag(TestTags.postItem).performClick()
        composeRule.onNodeWithTag(TestTags.postEditButton).performClick()

        //when
        val testText = "hi"
        composeRule.onNodeWithTag(TestTags.postEditTitleTextField).performTextInput(testText)
        composeRule.onNodeWithTag(TestTags.postEditSaveButton).performClick()

        //then
        repository.getPost().data?.test {
            val allPost = awaitItem()
            assertThat(allPost[0].title).contains(testText)
            assertThat(allPost[0].description).isEqualTo(post.description)
        }
    }

    @Test
    fun postEditScreenSaveSuccess_MoveToPostScreen(): Unit = runBlocking {
        //given
        composeRule.onNodeWithTag(TestTags.postBottomButton).performClick()
        composeRule.onNodeWithTag(TestTags.postItem).performClick()
        composeRule.onNodeWithTag(TestTags.postEditButton).performClick()

        //when
        val testText = "hi"
        composeRule.onNodeWithTag(TestTags.postEditTitleTextField).performTextInput(testText)
        composeRule.onNodeWithTag(TestTags.postEditSaveButton).performClick()

        //then
        composeRule.onNodeWithTag(TestTags.postItem).assertExists()
    }
}