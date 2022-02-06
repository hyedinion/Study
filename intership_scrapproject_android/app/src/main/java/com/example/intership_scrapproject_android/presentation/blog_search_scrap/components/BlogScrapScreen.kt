package com.example.intership_scrapproject_android.presentation.blog_search_scrap.components

import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.intership_scrapproject_android.data.remote.response.BlogSearchItem
import com.example.intership_scrapproject_android.presentation.blog_search.BlogSearchEvent
import com.example.intership_scrapproject_android.presentation.blog_search_scrap.BlogScrapEvent
import com.example.intership_scrapproject_android.presentation.blog_search_scrap.BlogScrapViewModel
import com.example.intership_scrapproject_android.presentation.main.bottom_bar.BottomBar
import com.example.intership_scrapproject_android.presentation.main.bottom_bar.BottomBarRoute
import com.example.intership_scrapproject_android.ui.theme.IntershipScrapProjectAndroidTheme

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
                    blog?.title = state.blogScrapTitle
                    blog?.description = state.blogScrapDescription
                    viewModel.onEvent(BlogScrapEvent.BlogScrapButtonClicked(
                        blogSearchItem = blog ?: BlogSearchItem(
                            bloggerlink = "",
                            bloggername = "",
                            title = "",
                            description = "",
                            link = "",
                            postdate = "",
                        ),
                        keyword = keyword ?: "",
                    ))
                },
            )
        },
        bottomBar = {
            BottomBar(navController = navController)
        },
        scaffoldState = scaffoldState
    ) {
        BlogScrapItem(
            item = blog ?: BlogSearchItem(
                bloggerlink = "",
                bloggername = "",
                title = "",
                description = "",
                link = "",
                postdate = "",
            ),
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
    }


}