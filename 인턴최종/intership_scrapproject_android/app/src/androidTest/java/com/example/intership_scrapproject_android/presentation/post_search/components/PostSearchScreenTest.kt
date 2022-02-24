package com.example.intership_scrapproject_android.presentation.post_search.components

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.navigation.compose.rememberNavController
import com.example.intership_scrapproject_android.AsyncTimer
import com.example.intership_scrapproject_android.data.local.Post
import com.example.intership_scrapproject_android.data.local.PostRepository
import com.example.intership_scrapproject_android.domain.use_case.GetPostUseCase
import com.example.intership_scrapproject_android.presentation.main.MainActivity
import com.example.intership_scrapproject_android.presentation.main.ScrapNavHost
import com.example.intership_scrapproject_android.presentation.post_search.PostSearchViewModel
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
class PostSearchScreenTest {

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeRule = createAndroidComposeRule<MainActivity>()

    @Inject
    lateinit var repository: PostRepository
    private val post1 = Post("title", "description", "link", "a", "20220125", "20220125", "bloggerName", id = 1)
    private val post2 = Post("title", "description", "link", "b", "20220126", "20220126", "bloggerName", id = 2)


    @Inject
    lateinit var useCase: GetPostUseCase
    private lateinit var viewModel: PostSearchViewModel

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
        viewModel = PostSearchViewModel(useCase)
        repository.insertPost(post1)
        repository.insertPost(post2)
    }

    @Test
    fun postSearchScreenExist(): Unit = runBlocking{
        //given
        composeRule.onNodeWithTag(TestTags.postBottomButton).performClick()

        //when
        composeRule.onNodeWithTag(TestTags.moveToPostSearchIcon).performClick()

        //then
        composeRule.onNodeWithTag(TestTags.postSearchAppBar).assertExists()
    }

    @Test
    fun postSearch_Success_withTitle(): Unit = runBlocking{
        //given
        val query = "title"
        val delay = 500L
        composeRule.onNodeWithTag(TestTags.postBottomButton).performClick()
        composeRule.onNodeWithTag(TestTags.moveToPostSearchIcon).performClick()

        //when
        composeRule.onNodeWithTag(TestTags.postSearchAppBar).performTextInput(query)
        composeRule.onNodeWithTag(TestTags.postSearchAppBar).performImeAction()
        AsyncTimer.start (delay)
        composeRule.waitUntil (
            condition = { AsyncTimer.expired},
            timeoutMillis = delay + 1000
        )

        //then
        composeRule.onNodeWithTag(TestTags.postSearchItem).assertExists()
        //composeRule.onNodeWithTag(TestTags.postSearchItemTitle).assertTextContains(query,true)

    }

    @Test
    fun postSearch_Success_withDescription(): Unit = runBlocking{
        //given
        val query = "description"
        val delay = 500L
        composeRule.onNodeWithTag(TestTags.postBottomButton).performClick()
        composeRule.onNodeWithTag(TestTags.moveToPostSearchIcon).performClick()

        //when
        composeRule.onNodeWithTag(TestTags.postSearchAppBar).performTextInput(query)
        composeRule.onNodeWithTag(TestTags.postSearchAppBar).performImeAction()
        AsyncTimer.start (delay)
        composeRule.waitUntil (
            condition = { AsyncTimer.expired},
            timeoutMillis = delay + 1000
        )

        //then
        composeRule.onNodeWithTag(TestTags.postSearchItem).assertExists()
        //composeRule.onNodeWithTag(TestTags.postSearchItemDescription).assertTextContains(query,true)

    }

    @Test
    fun postSearchScreen_NoResultText(): Unit = runBlocking{
        //given
        composeRule.onNodeWithTag(TestTags.postBottomButton).performClick()

        //when
        composeRule.onNodeWithTag(TestTags.moveToPostSearchIcon).performClick()

        //then
        composeRule.onNodeWithTag(TestTags.postSearchResultNullText).assertExists()
    }
}