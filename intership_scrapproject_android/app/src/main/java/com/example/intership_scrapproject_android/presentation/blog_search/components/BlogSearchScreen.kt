package com.example.my_first.presentation.Search

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.intership_scrapproject_android.data.remote.response.BlogSearchItem
import com.example.intership_scrapproject_android.presentation.blog_search.BlogSearchEvent
import com.example.intership_scrapproject_android.presentation.blog_search.BlogSearchViewModel
import com.example.intership_scrapproject_android.presentation.blog_search.components.BlogSearchAppBar
import com.example.intership_scrapproject_android.presentation.blog_search.components.BlogSearchErrorSnackBar
import com.example.intership_scrapproject_android.presentation.blog_search.components.BlogSearchGrid
import com.example.intership_scrapproject_android.presentation.blog_search.components.BlogSearchList
import com.example.intership_scrapproject_android.presentation.main.bottom_bar.BottomBar
import com.example.intership_scrapproject_android.ui.theme.IntershipScrapProjectAndroidTheme
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import kotlinx.coroutines.launch

@ExperimentalComposeUiApi
@Composable
fun BlogSearchScreen(
    navController: NavHostController,
    viewModel : BlogSearchViewModel = hiltViewModel()
){
    val state = viewModel.state.value
    val scaffoldState = rememberScaffoldState()
    val lazyBlogSearchItems: LazyPagingItems<BlogSearchItem>? = state.blogSearchResult?.collectAsLazyPagingItems()

    Scaffold(
        topBar = {
            BlogSearchAppBar(
                text = state.blogSearchText,
                isBlogSearchLayoutList = state.isBlogSearchLayoutList,
                onTextChange = {viewModel.onEvent(BlogSearchEvent.BlogSearchQueryChange(it))},
                onSearchClicked = {viewModel.onEvent(BlogSearchEvent.GetBlogSearchResult(it))},
                onBlogSearchLayoutChange = {viewModel.onEvent(BlogSearchEvent.BlogSearchLayoutChange)}
            )
        },
        bottomBar = {
            BottomBar(navController = navController)
        },
        scaffoldState = scaffoldState
    ) {
        LaunchedEffect(state.isRefreshing){
            if(state.isRefreshing){
                launch {
                    lazyBlogSearchItems?.refresh()
                }.join()
                viewModel.onEvent(BlogSearchEvent.Refreshing)
            }
        }

        SwipeRefresh(
            state = rememberSwipeRefreshState(isRefreshing = state.isRefreshing),
            onRefresh = {viewModel.onEvent(BlogSearchEvent.Refreshing)}
        ) {
            Box (
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ){
                if (lazyBlogSearchItems == null) {
                    Text(
                        modifier = Modifier
                            .align(Alignment.Center)
                            .padding(bottom = 40.dp),
                        text = "검색어를 입력해주세요."
                    )
                } else if( lazyBlogSearchItems.loadState.refresh!= LoadState.Loading && lazyBlogSearchItems.itemCount == 0 ){
                    Text(
                        modifier = Modifier
                            .align(Alignment.Center)
                            .padding(bottom = 40.dp),
                        text = "검색 결과가 없습니다."
                    )
                } else {
                    if (state.isBlogSearchLayoutList) {
                        BlogSearchList(lazyBlogSearchItems)
                    } else {
                        BlogSearchGrid(lazyBlogSearchItems)
                    }
                }
            }
        }

        lazyBlogSearchItems?.apply {
            when {
                loadState.refresh is LoadState.Loading -> {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                    ) {
                        CircularProgressIndicator(
                            modifier = Modifier
                                .align(Alignment.Center),
                            color = Color.LightGray
                        )
                    }
                }
                loadState.refresh is LoadState.Error -> {
                    val e = lazyBlogSearchItems.loadState.refresh as LoadState.Error
                    BlogSearchErrorSnackBar(e) { retry() }

                }
                loadState.append is LoadState.Error -> {
                    val e = lazyBlogSearchItems.loadState.append as LoadState.Error
                    BlogSearchErrorSnackBar(e) { retry() }
                }

            }
        }

    }
}

@ExperimentalComposeUiApi
@Preview(showBackground = true)
@Composable
fun BlogSearchScreenPreview() {
    val navController = rememberNavController()
    BlogSearchScreen(navController)

}