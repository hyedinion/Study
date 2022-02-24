package com.example.intership_scrapproject_android.presentation.post.components

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.navigation.compose.rememberNavController
import com.example.intership_scrapproject_android.data.local.Post
import com.example.intership_scrapproject_android.data.local.PostRepository
import com.example.intership_scrapproject_android.domain.use_case.GetPostUseCase
import com.example.intership_scrapproject_android.presentation.main.MainActivity
import com.example.intership_scrapproject_android.presentation.main.ScrapNavHost
import com.example.intership_scrapproject_android.presentation.post.PostViewModel
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
class PostScreenTest {

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeRule = createAndroidComposeRule<MainActivity>()

    @Inject
    lateinit var repository : PostRepository
    private val post = Post("title","description","link","a","20220125","20220125","bloggerName",id=1)

    @Inject
    lateinit var useCase : GetPostUseCase
    private lateinit var viewModel : PostViewModel

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
    fun setup() : Unit = runBlocking {
        hiltRule.inject()
        composeRule.setContent {
            IntershipScrapProjectAndroidTheme {
                val navController = rememberNavController()
                ScrapNavHost(navController = navController)
            }
        }
        viewModel = PostViewModel(useCase)
        repository.insertPost(post)
    }

    @Test
    fun postScreenExist(): Unit = runBlocking{
        //when
        composeRule.onNodeWithTag(TestTags.postBottomButton).performClick()

        //then
        composeRule.onNodeWithTag(TestTags.postLazyColumn).assertExists()
        composeRule.onNodeWithTag(TestTags.postOrderSection).assertDoesNotExist()
    }

    @Test
    fun postScreenSortIconClick_dropDownOrderSection(): Unit = runBlocking{
        //given
        composeRule.onNodeWithTag(TestTags.postBottomButton).performClick()

        //when
        composeRule.onNodeWithTag(TestTags.postSortIconButton).performClick()

        //then
        composeRule.onNodeWithTag(TestTags.postOrderSection).assertExists()
    }

    @Test
    fun postScreenSortIconClick_dropDownOrderSection_close(): Unit = runBlocking{
        //given
        composeRule.onNodeWithTag(TestTags.postBottomButton).performClick()

        //when
        composeRule.onNodeWithTag(TestTags.postSortIconButton).performClick()
        composeRule.onNodeWithTag(TestTags.postSortIconButton).performClick()
        //then
        composeRule.onNodeWithTag(TestTags.postOrderSection).assertDoesNotExist()
    }

    @Test
    fun postScreenOrderSection_changeText(): Unit = runBlocking{
        //given
        composeRule.onNodeWithTag(TestTags.postBottomButton).performClick()
        composeRule.onNodeWithTag(TestTags.postSortIconButton).performClick()

        //when
        composeRule.onNodeWithTag(TestTags.postScrapDateTextButton).performClick()

        //then
        composeRule.onNodeWithTag(TestTags.postAppBarOrderText).assertTextEquals("최근 스크랩순")

        composeRule.onNodeWithTag(TestTags.postPostDateTextButton).performClick()
        composeRule.onNodeWithTag(TestTags.postAppBarOrderText).assertTextEquals("최근 등록순")

        composeRule.onNodeWithTag(TestTags.postKeywordTextButton).performClick()
        composeRule.onNodeWithTag(TestTags.postAppBarOrderText).assertTextEquals("키워드별")
    }

    @Test
    fun postScreenChangeTextColor(): Unit = runBlocking{
        //given
        composeRule.onNodeWithTag(TestTags.postBottomButton).performClick()
        composeRule.onNodeWithTag(TestTags.postSortIconButton).performClick()

        //when
        composeRule.onNodeWithTag(TestTags.postScrapDateTextButton).performClick()

        //then
        composeRule.onNodeWithTag(TestTags.postAppBarOrderText).assertTextEquals("최근 스크랩순")

        composeRule.onNodeWithTag(TestTags.postPostDateTextButton).performClick()
        composeRule.onNodeWithTag(TestTags.postAppBarOrderText).assertTextEquals("최근 등록순")

        composeRule.onNodeWithTag(TestTags.postKeywordTextButton).performClick()
        composeRule.onNodeWithTag(TestTags.postAppBarOrderText).assertTextEquals("키워드별")
    }

    @Test
    fun postScreenItem(): Unit = runBlocking{
        //given
        composeRule.onNodeWithTag(TestTags.postBottomButton).performClick()

        //then
        composeRule.onNodeWithTag(TestTags.postItem).assertExists()
    }

    @Test
    fun postScreenItemHeader(): Unit = runBlocking{
        //given
        composeRule.onNodeWithTag(TestTags.postBottomButton).performClick()
        composeRule.onNodeWithTag(TestTags.postSortIconButton).performClick()

        //when
        composeRule.onNodeWithTag(TestTags.postKeywordTextButton).performClick()

        //when
        composeRule.onNodeWithTag(TestTags.postAppBarOrderText).assertTextEquals("키워드별")
        composeRule.onNodeWithTag(TestTags.postItemHeader).assertExists()

    }

}
