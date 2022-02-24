package com.example.intership_scrapproject_android.presentation.blog_search_scrap.components

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.navigation.compose.rememberNavController
import app.cash.turbine.test
import com.example.intership_scrapproject_android.AsyncTimer
import com.example.intership_scrapproject_android.FakeBlogSearchResponse
import com.example.intership_scrapproject_android.data.local.PostRepository
import com.example.intership_scrapproject_android.data.remote.BlogSearchAPI
import com.example.intership_scrapproject_android.domain.use_case.InsertPostUseCase
import com.example.intership_scrapproject_android.presentation.blog_search_scrap.BlogScrapViewModel
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
import org.mockito.ArgumentMatchers.anyInt
import org.mockito.ArgumentMatchers.anyString
import org.mockito.BDDMockito.given
import javax.inject.Inject

@RunWith(Parameterized::class)
@HiltAndroidTest
class BlogScrapScreenTest {

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeRule = createAndroidComposeRule<MainActivity>()

    @Inject
    lateinit var blogSearchApi: BlogSearchAPI
    private val delay = 500L
    private val query = "happy"

    @Inject
    lateinit var repository : PostRepository
    private val item = FakeBlogSearchResponse.fakeBlogSearchResult.items[0]

    @Inject
    lateinit var useCase : InsertPostUseCase
    lateinit var viewModel : BlogScrapViewModel

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
    fun setup() {
        hiltRule.inject()
        composeRule.setContent {
            IntershipScrapProjectAndroidTheme {
                val navController = rememberNavController()
                ScrapNavHost(navController = navController)
            }
        }
        viewModel = BlogScrapViewModel(useCase)
    }


    @Test
    fun blogDetailScreenMoveToScrapScreen_Success(): Unit = runBlocking{
        //given
        given(blogSearchApi.searchBlog(anyString(),anyString(),anyString(), anyInt(),anyInt()))
            .willReturn(FakeBlogSearchResponse.fakeBlogSearchResult)
        composeRule.onNodeWithTag(TestTags.blogSearchTextField).performTextInput(query)
        composeRule.onNodeWithTag(TestTags.blogSearchTextField).performImeAction()
        AsyncTimer.start (delay)
        composeRule.waitUntil (
            condition = {AsyncTimer.expired},
            timeoutMillis = delay + 1000
        )
        composeRule.onNodeWithTag(TestTags.blogSearchListItemBox).performClick()

        //when
        composeRule.onNodeWithTag(TestTags.blogScrapButton).performClick()

        //then
        composeRule.onNodeWithTag(TestTags.blogScrapItem).assertExists()
    }

    @Test
    fun blogScrapScreenItemTextCheck(): Unit = runBlocking{
        //given
        given(blogSearchApi.searchBlog(anyString(),anyString(),anyString(), anyInt(),anyInt()))
            .willReturn(FakeBlogSearchResponse.fakeBlogSearchResult)
        composeRule.onNodeWithTag(TestTags.blogSearchTextField).performTextInput(query)
        composeRule.onNodeWithTag(TestTags.blogSearchTextField).performImeAction()
        AsyncTimer.start (delay)
        composeRule.waitUntil (
            condition = {AsyncTimer.expired},
            timeoutMillis = delay + 1000
        )
        composeRule.onNodeWithTag(TestTags.blogSearchListItemBox).performClick()

        //when
        composeRule.onNodeWithTag(TestTags.blogScrapButton).performClick()

        //then
        composeRule.onNodeWithTag(TestTags.blogScrapItem).assertExists()
        composeRule.onNodeWithTag(TestTags.blogScrapItemKeyword).assertTextEquals(query)
        composeRule.onNodeWithTag(TestTags.blogScrapItemTitle).assertTextEquals(item.title)
    }

    @Test
    fun blogScrapScreenTitleDescriptionEditCheck(): Unit = runBlocking{
        //given
        given(blogSearchApi.searchBlog(anyString(),anyString(),anyString(), anyInt(),anyInt()))
            .willReturn(FakeBlogSearchResponse.fakeBlogSearchResult)
        composeRule.onNodeWithTag(TestTags.blogSearchTextField).performTextInput(query)
        composeRule.onNodeWithTag(TestTags.blogSearchTextField).performImeAction()
        AsyncTimer.start (delay)
        composeRule.waitUntil (
            condition = {AsyncTimer.expired},
            timeoutMillis = delay + 1000
        )
        composeRule.onNodeWithTag(TestTags.blogSearchListItemBox).performClick()

        //when
        composeRule.onNodeWithTag(TestTags.blogScrapButton).performClick()

        //then
        composeRule.onNodeWithTag(TestTags.blogScrapItem).assertExists()
        val testText = "hi"
        composeRule.onNodeWithTag(TestTags.blogScrapItemTitle).performTextInput(testText)
        composeRule.onNodeWithTag(TestTags.blogScrapItemTitle).assertTextContains(testText,true)
        composeRule.onNodeWithTag(TestTags.blogScrapItemDescription).performTextInput(testText)
        composeRule.onNodeWithTag(TestTags.blogScrapItemDescription).assertTextContains(testText,true)
    }

    @Test
    fun blogScrapScreenScrap_Success(): Unit = runBlocking{
        //given
        given(blogSearchApi.searchBlog(anyString(),anyString(),anyString(), anyInt(),anyInt()))
            .willReturn(FakeBlogSearchResponse.fakeBlogSearchResult)
        composeRule.onNodeWithTag(TestTags.blogSearchTextField).performTextInput(query)
        composeRule.onNodeWithTag(TestTags.blogSearchTextField).performImeAction()
        AsyncTimer.start (delay)
        composeRule.waitUntil (
            condition = {AsyncTimer.expired},
            timeoutMillis = delay + 1000
        )
        composeRule.onNodeWithTag(TestTags.blogSearchListItemBox).performClick()

        //when
        composeRule.onNodeWithTag(TestTags.blogScrapButton).performClick()
        composeRule.onNodeWithTag(TestTags.blogScrapSaveButton).performClick()

        //then
        val postItem = viewModel.blogSearchItemToPost(item,query)
        repository.getPost().data?.test {
            val allPost = awaitItem()
            assertThat(allPost[0].title).isEqualTo(postItem.title)
            assertThat(allPost[0].description).isEqualTo(postItem.description)
        }
    }

    @Test
    fun blogScrapScreenMoveToBlogSearchScreen_Success(): Unit = runBlocking{
        //given
        given(blogSearchApi.searchBlog(anyString(),anyString(),anyString(), anyInt(),anyInt()))
            .willReturn(FakeBlogSearchResponse.fakeBlogSearchResult)
        composeRule.onNodeWithTag(TestTags.blogSearchTextField).performTextInput(query)
        composeRule.onNodeWithTag(TestTags.blogSearchTextField).performImeAction()
        AsyncTimer.start (delay)
        composeRule.waitUntil (
            condition = {AsyncTimer.expired},
            timeoutMillis = delay + 1000
        )
        composeRule.onNodeWithTag(TestTags.blogSearchListItemBox).performClick()

        //when
        composeRule.onNodeWithTag(TestTags.blogScrapButton).performClick()
        composeRule.onNodeWithTag(TestTags.blogScrapSaveButton).performClick()

        //then
        composeRule.onNodeWithTag(TestTags.blogSearchTextField).assertExists()
    }
}