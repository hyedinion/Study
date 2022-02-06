package com.example.intership_scrapproject_android.presentation.blog_search.components

import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.intership_scrapproject_android.BlogSearchResult
import com.example.intership_scrapproject_android.core.util.TestTags
import com.example.intership_scrapproject_android.data.remote.BlogSearchAPI
import com.example.intership_scrapproject_android.presentation.main.MainActivity
import com.example.intership_scrapproject_android.presentation.main.bottom_bar.BottomBarRoute
import com.example.intership_scrapproject_android.ui.theme.IntershipScrapProjectAndroidTheme
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.anyInt
import org.mockito.ArgumentMatchers.anyString
import org.mockito.BDDMockito.given
import javax.inject.Inject

@RunWith(AndroidJUnit4::class)
@HiltAndroidTest
class BlogSearchScreenTest{

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeRule = createAndroidComposeRule<MainActivity>()

    @Inject lateinit var blogSearchApi : BlogSearchAPI


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
        //val context = ApplicationProvider.getApplicationContext<Context>()
        composeRule.onNodeWithTag(TestTags.RowButton).assertDoesNotExist()
        composeRule.onNodeWithContentDescription("grid icon").performClick()
        composeRule.onNodeWithContentDescription("list icon").assertExists()
        composeRule.onNodeWithContentDescription("grid icon").assertDoesNotExist()
    }

    @Test
    fun searchBlogWithQuery_Success(): Unit = runBlocking{
        val query = "happy"

        given(blogSearchApi.searchBlog(anyString(),anyString(),anyString(), anyInt(),anyInt()))
            .willReturn(BlogSearchResult.blogSearchResult)
        composeRule.onNodeWithTag("searchBar").performTextInput(query)
        composeRule.onNodeWithTag("searchBar").performImeAction()
        composeRule.onNodeWithTag("blogSearchLazyColumn").assertExists()
    }



}