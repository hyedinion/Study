package com.example.intership_scrapproject_android.presentation.post_detail.components

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.navigation.compose.rememberNavController
import com.example.intership_scrapproject_android.data.local.Post
import com.example.intership_scrapproject_android.data.local.PostRepository
import com.example.intership_scrapproject_android.domain.use_case.DeletePostUseCase
import com.example.intership_scrapproject_android.presentation.main.MainActivity
import com.example.intership_scrapproject_android.presentation.main.ScrapNavHost
import com.example.intership_scrapproject_android.presentation.post_detail.PostDetailViewModel
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
class PostDetailScreenTest {

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeRule = createAndroidComposeRule<MainActivity>()

    @Inject
    lateinit var repository: PostRepository
    private val post = Post("title", "description", "link", "a", "20220125", "20220125", "bloggerName", id = 1)

    @Inject
    lateinit var useCase: DeletePostUseCase
    private lateinit var viewModel: PostDetailViewModel

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
        viewModel = PostDetailViewModel(useCase)
        repository.insertPost(post)
    }

    @Test
    fun postDetailScreenExist(): Unit = runBlocking{
        //given
        composeRule.onNodeWithTag(TestTags.postBottomButton).performClick()

        //when
        composeRule.onNodeWithTag(TestTags.postItem).performClick()

        //then
        composeRule.onNodeWithTag(TestTags.postDetailItem).assertExists()
    }

    @Test
    fun postDetailScreenBackPress(): Unit = runBlocking{
        //given
        composeRule.onNodeWithTag(TestTags.postBottomButton).performClick()
        composeRule.onNodeWithTag(TestTags.postItem).performClick()

        //when
        composeRule.onNodeWithTag(TestTags.postDetailBackButton).performClick()

        //then
        composeRule.onNodeWithTag(TestTags.postItem).assertExists()
    }

    @Test
    fun postDetailDeleteItem_Success(): Unit = runBlocking{
        //given
        composeRule.onNodeWithTag(TestTags.postBottomButton).performClick()
        composeRule.onNodeWithTag(TestTags.postItem).performClick()

        //when
        composeRule.onNodeWithTag(TestTags.postDeleteButton).performClick()

        //then
        composeRule.onNodeWithTag(TestTags.postItem).assertDoesNotExist()
    }

}