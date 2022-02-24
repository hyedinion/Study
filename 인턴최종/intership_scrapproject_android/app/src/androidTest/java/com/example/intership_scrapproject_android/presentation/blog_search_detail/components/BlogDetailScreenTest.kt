package com.example.intership_scrapproject_android.presentation.blog_search_detail.components

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.navigation.compose.rememberNavController
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.intership_scrapproject_android.AsyncTimer
import com.example.intership_scrapproject_android.FakeBlogSearchResponse
import com.example.intership_scrapproject_android.data.remote.BlogSearchAPI
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
import org.mockito.ArgumentMatchers.anyInt
import org.mockito.ArgumentMatchers.anyString
import org.mockito.BDDMockito.given
import javax.inject.Inject

@RunWith(Parameterized::class)
@HiltAndroidTest
class BlogDetailScreenTest {

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeRule = createAndroidComposeRule<MainActivity>()

    @Inject
    lateinit var blogSearchApi: BlogSearchAPI
    private val delay = 500L
    private val query = "happy"

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
    }

    @Test
    fun blogSearchScreenMoveToSearch_Success(): Unit = runBlocking{
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

        //when
        composeRule.onNodeWithTag(TestTags.blogSearchListItemBox).performClick()

        //then
        composeRule.onNodeWithTag(TestTags.blogSearchDetailItem).assertExists()
    }

    @Test
    fun blogSearchDetail_keyword_Equal(): Unit = runBlocking{
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

        //when
        composeRule.onNodeWithTag(TestTags.blogSearchListItemBox).performClick()

        //then
        composeRule.onNodeWithTag(TestTags.blogSearchDetailKeywordText).assertTextEquals(query)
    }

    @Test
    fun blogSearchDetail_blogTitle_Equal(): Unit = runBlocking{
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

        //when
        composeRule.onNodeWithTag(TestTags.blogSearchListItemBox).performClick()

        //then
        val fakeTitle = FakeBlogSearchResponse.fakeBlogSearchResult.items[0].title
        composeRule.onNodeWithTag(TestTags.blogSearchDetailBlogTitle).assertTextEquals(fakeTitle)
    }
}