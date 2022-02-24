package com.example.intership_scrapproject_android.presentation.blog_search.components

import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.intership_scrapproject_android.AsyncTimer
import com.example.intership_scrapproject_android.FakeBlogSearchResponse
import com.example.intership_scrapproject_android.data.remote.BlogSearchAPI
import com.example.intership_scrapproject_android.presentation.main.MainActivity
import com.example.intership_scrapproject_android.presentation.main.bottom_bar.BottomBarRoute
import com.example.intership_scrapproject_android.ui.theme.IntershipScrapProjectAndroidTheme
import com.example.intership_scrapproject_android.util.TestTags
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.runBlocking
import okhttp3.MediaType
import okhttp3.ResponseBody
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import org.mockito.ArgumentMatchers.anyInt
import org.mockito.ArgumentMatchers.anyString
import org.mockito.BDDMockito.given
import retrofit2.HttpException
import retrofit2.Response
import javax.inject.Inject


@RunWith(Parameterized::class)
@HiltAndroidTest
class BlogSearchScreenTest{

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeRule = createAndroidComposeRule<MainActivity>()

    @Inject lateinit var blogSearchApi : BlogSearchAPI

    companion object {
        private const val numberOfTests = 1

        @JvmStatic
        @Parameterized.Parameters
        fun data(): Array<Array<Any?>> = Array(numberOfTests) { arrayOfNulls<Any?>(0) }
    }

    @ExperimentalComposeUiApi
    @Before
    fun setup(){
        hiltRule.inject()

        composeRule.setContent {
            val navController = rememberNavController()
            IntershipScrapProjectAndroidTheme{
                NavHost(
                    navController = navController,
                    startDestination = BottomBarRoute.BlogSearch.route
                ){
                    composable(route = BottomBarRoute.BlogSearch.route){
                        BlogSearchScreen(navController = navController)
                    }
                }
            }
        }
    }

    @Test
    fun clickLayoutChangeButton_GridToRow(){
        //when
        composeRule.onNodeWithContentDescription("grid icon").performClick()
        //then
        composeRule.onNodeWithContentDescription("list icon").assertExists()
        composeRule.onNodeWithContentDescription("grid icon").assertDoesNotExist()
    }

    @Test
    fun searchBlogListWithQuery_Success(): Unit = runBlocking{
        //given
        val query = "happy"
        val delay = 500L
        given(blogSearchApi.searchBlog(anyString(),anyString(),anyString(), anyInt(),anyInt()))
            .willReturn(FakeBlogSearchResponse.fakeBlogSearchResult)

        //when
        composeRule.onNodeWithTag(TestTags.blogSearchTextField).performTextInput(query)
        composeRule.onNodeWithTag(TestTags.blogSearchTextField).performImeAction()
        AsyncTimer.start (delay)

        //then
        composeRule.waitUntil (
            condition = {AsyncTimer.expired},
            timeoutMillis = delay + 1000
        )
        composeRule.onNodeWithTag(TestTags.blogSearchLazyColumn).assertExists()
    }

    @Test
    fun searchBlogListWithQuery_Success_LinkExist(): Unit = runBlocking{
        //given
        val query = "happy"
        val delay = 1000L
        given(blogSearchApi.searchBlog(anyString(),anyString(),anyString(), anyInt(),anyInt()))
            .willReturn(FakeBlogSearchResponse.fakeLinkExistBlogSearchResult)

        //when
        composeRule.onNodeWithTag(TestTags.blogSearchTextField).performTextInput(query)
        composeRule.onNodeWithTag(TestTags.blogSearchTextField).performImeAction()
        AsyncTimer.start (delay)

        //then
        composeRule.waitUntil (
            condition = {AsyncTimer.expired},
            timeoutMillis = delay + 1000
        )
        composeRule.onNodeWithTag(TestTags.blogSearchListItemBox).assertExists()
        composeRule.onNodeWithContentDescription("scrapped icon").assertExists()
    }

    @Test
    fun searchBlogGridWithQuery_Success(): Unit = runBlocking{
        //given
        val query = "happy"
        given(blogSearchApi.searchBlog(anyString(),anyString(),anyString(), anyInt(),anyInt()))
            .willReturn(FakeBlogSearchResponse.fakeBlogSearchResult)

        //when
        composeRule.onNodeWithTag(TestTags.blogSearchTextField).performTextInput(query)
        composeRule.onNodeWithTag(TestTags.blogSearchTextField).performImeAction()
        composeRule.onNodeWithContentDescription("grid icon").performClick()

        //then
        composeRule.onNodeWithTag(TestTags.blogSearchLazyVerticalGrid).assertExists()
    }

    @Test
    fun searchBlogWithQuery_Fail_HttpError(): Unit = runBlocking{
        //given
        val query = "happy"
        val error = HttpException(Response.error<Any>(404, ResponseBody.create(MediaType.parse("plain/text"),"content") ))
        given(blogSearchApi.searchBlog(anyString(),anyString(),anyString(), anyInt(),anyInt()))
            .willThrow(error)

        //when
        composeRule.onNodeWithTag(TestTags.blogSearchTextField).performTextInput(query)
        composeRule.onNodeWithTag(TestTags.blogSearchTextField).performImeAction()

        //then
        composeRule.onNodeWithTag(TestTags.blogSearchErrorSnackBar).assertExists()
    }

    @Test
    fun insertBlogSearchQueryTextExist(): Unit = runBlocking{
        //given
        val query = "happy"
        given(blogSearchApi.searchBlog(anyString(),anyString(),anyString(), anyInt(),anyInt()))
            .willReturn(FakeBlogSearchResponse.fakeBlogSearchResult)
        composeRule.onNodeWithTag(TestTags.insertBlogSearchQueryText).assertExists()

        //when
        composeRule.onNodeWithTag(TestTags.blogSearchTextField).performTextInput(query)
        composeRule.onNodeWithTag(TestTags.blogSearchTextField).performImeAction()

        //then
        composeRule.onNodeWithTag(TestTags.insertBlogSearchQueryText).assertDoesNotExist()
    }

    @Test
    fun blogSearchResultNullTextExist(): Unit = runBlocking{
        //given
        val query = "happy"
        given(blogSearchApi.searchBlog(anyString(),anyString(),anyString(), anyInt(),anyInt()))
            .willReturn(FakeBlogSearchResponse.fakeZeroBlogSearchResult)
        composeRule.onNodeWithTag(TestTags.blogSearchResultNullText).assertDoesNotExist()

        //when
        composeRule.onNodeWithTag(TestTags.blogSearchTextField).performTextInput(query)
        composeRule.onNodeWithTag(TestTags.blogSearchTextField).performImeAction()

        //then
        composeRule.onNodeWithTag(TestTags.blogSearchResultNullText).assertExists()
    }

}