package com.example.my_first.presentation.Search

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.my_first.data.remote.Item
import com.example.my_first.presentation.BottomBar
import com.example.my_first.presentation.Search.components.SearchAppBar
import com.example.my_first.presentation.Search.components.SearchItem
import com.example.my_first.ui.theme.My_firstTheme
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import kotlinx.coroutines.delay

@Composable
fun SearchScreen(
    navController: NavHostController,
    viewModel : SearchViewModel = hiltViewModel()
){
    val state = viewModel.state.value
    val scaffoldState = rememberScaffoldState()
    val lazySearchItems: LazyPagingItems<Item>? = state.searchList?.collectAsLazyPagingItems()

    Scaffold(
        topBar = {
            SearchAppBar(
            text = state.searchText,
            onTextChange = {viewModel.onEvent(SearchEvent.queryChange(it))},
            onSearchClicked = { viewModel.onEvent(SearchEvent.searchQueryInsert(it)) }
        )
        },
        bottomBar = { BottomBar(navController = navController) },
        scaffoldState = scaffoldState
    ) {
        var refreshing by remember { mutableStateOf(false) }
        LaunchedEffect(refreshing){
            if(refreshing){
                Log.d("refresh","refresh")
                lazySearchItems?.refresh()
                refreshing = false
            }
        }

        SwipeRefresh(
            state = rememberSwipeRefreshState(isRefreshing = refreshing),
            onRefresh = {refreshing = true}
        ) {
            Box (
                contentAlignment = Alignment.Center
            ){
                if (lazySearchItems != null) {
                    if (state.isSearchOrderList) {
                        SearchList(lazySearchItems)
                    } else {
                        SearchGrid(lazySearchItems)
                    }
                } else {
                    Text(text = "검색어를 입력하세요")
                }
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    contentAlignment = Alignment.TopEnd
                ) {
                    Switch(
                        checked = state.isSearchOrderList,
                        onCheckedChange = {
                            viewModel.onEvent(SearchEvent.searchOrder)
                        },
                        colors = SwitchDefaults.colors(MaterialTheme.colors.primary)
                    )
                }

            }
        }
    }
}

@Composable
fun SearchList(
    result: LazyPagingItems<Item>?
){
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)//item간의 간격
    ) {
        if (result != null) {
            items(result?.itemCount) { i ->
                SearchItem(
                    item = result[i]!!,
                    modifier = Modifier
                        .clickable {

                        },
                    id = "읽음"
                )
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SearchGrid(
    result : LazyPagingItems<Item>?
){
    LazyVerticalGrid(
        cells = GridCells.Fixed(2),
        modifier = Modifier
            .fillMaxWidth(),
        contentPadding = PaddingValues(16.dp)
    ){
        if (result!=null) {
            items(result.itemCount) { i ->
                SearchItem(
                    item = result[i]!!,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {

                        },
                    id = i.toString()
                )
            }
        }
        item {
            Button(modifier = Modifier,onClick = { result?.retry() }) {
                Log.d("retry","retry")
                Icon(imageVector = Icons.Default.Refresh, contentDescription = "refresh")
            }
            Spacer(modifier = Modifier.padding(bottom =200.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ScreenListPreview() {
    My_firstTheme {
    }
}

@Preview(showBackground = true)
@Composable
fun ScreenGridPreview() {
    My_firstTheme {
    }
}