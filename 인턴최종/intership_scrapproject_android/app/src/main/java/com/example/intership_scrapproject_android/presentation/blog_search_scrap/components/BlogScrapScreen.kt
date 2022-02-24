package com.example.intership_scrapproject_android.presentation.blog_search_scrap.components

import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.intership_scrapproject_android.data.remote.response.BlogSearchItem
import com.example.intership_scrapproject_android.presentation.blog_search_scrap.BlogScrapEvent
import com.example.intership_scrapproject_android.presentation.blog_search_scrap.BlogScrapViewModel
import com.example.intership_scrapproject_android.presentation.main.bottom_bar.BottomBar
import com.example.intership_scrapproject_android.presentation.main.bottom_bar.BottomBarRoute


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun BlogScrapScreen(
    navController: NavHostController,
    blog: BlogSearchItem?,
    keyword: String?,
    viewModel : BlogScrapViewModel = hiltViewModel()
){
    val state = viewModel.state.value
    val scaffoldState = rememberScaffoldState()
    val emptyBlogSearchItem = BlogSearchItem( "", "", "", "", "", "",)
    if (state.initBlogScrapPost) {
        viewModel.onEvent(
            BlogScrapEvent.InitBlogScrapTitleDescription(
                blog?.title ?: "",
                blog?.description ?: ""
            )
        )
    }
    Scaffold(
        topBar = {
            BlogScrapAppBar(
                onBackPress = {navController.popBackStack()},
                onInsertButtonPress = {
                    viewModel.onEvent(
                        BlogScrapEvent.BlogScrapButtonClicked(
                            blogSearchItem = BlogSearchItem(
                                bloggerlink = blog?.bloggerlink?:"",
                                bloggername = blog?.bloggername?:"",
                                title = state.blogScrapTitle,
                                description = state.blogScrapDescription,
                                link = blog?.link?:"",
                                postdate = blog?.postdate?:"",
                            ),
                            keyword = keyword ?: "",
                        )
                    )
                    blog?.linkExist = true
                },
            )
        },
        bottomBar = {
            BottomBar(navController = navController)
        },
        scaffoldState = scaffoldState
    ) {
        BlogScrapItem(
            item = blog ?: emptyBlogSearchItem,
            keyword = keyword ?: "",
            title = state.blogScrapTitle,
            onTitleTextChange = {viewModel.onEvent(BlogScrapEvent.BlogScrapTitleChange(it))},
            description = state.blogScrapDescription,
            onDescriptionTextChange = {viewModel.onEvent(BlogScrapEvent.BlogScrapDescriptionChange(it))},
        )
    }

    if (state.showInsertErrorToastMessage){
        viewModel.onEvent(BlogScrapEvent.ShowInsertErrorToastHandled)
        Toast.makeText(LocalContext.current,state.insertErrorToastMessage, Toast.LENGTH_LONG).show()
    }

    if (state.showBlankToastMessage){
        viewModel.onEvent(BlogScrapEvent.ShowBlankErrorToastHandled)
        Toast.makeText(LocalContext.current,state.blankErrorToastMessage, Toast.LENGTH_LONG).show()
    }

    if (state.saveBlogSuccess){
        navController.popBackStack(BottomBarRoute.BlogSearch.route,false)
        viewModel.onEvent(BlogScrapEvent.MoveScreenHandled)
        Toast.makeText(LocalContext.current,"blog가 Scrap되었습니다.", Toast.LENGTH_LONG).show()
    }


}