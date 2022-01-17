package com.example.my_first.presentation.Search

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.my_first.presentation.BottomBar
import com.example.my_first.presentation.BottomBarScreen
import com.example.my_first.presentation.Search.components.SearchAppBar
import com.example.my_first.presentation.scrap.ScrapViewModel
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

    Scaffold(
        topBar = { SearchAppBar(
            text = "",
            onTextChange = {},
            onCloseClicked = {},
            onSearchClicked = {}
        )
        },
        bottomBar = { BottomBar(navController = navController) },
        scaffoldState = scaffoldState
    ) {
        var refreshing by remember { mutableStateOf(false) }
        LaunchedEffect(refreshing){
            if(refreshing){
                delay(2000)
                refreshing = false
            }
        }

        SwipeRefresh(
            state = rememberSwipeRefreshState(isRefreshing = refreshing),
            onRefresh = {refreshing = true}
        ) {
            Box(
            ) {
                if (state.isSearchOrderList) {
                    SearchListScreen()
                } else {
                    SearchGridScreen()
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
fun SearchListScreen(
){
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)//item간의 간격
    ) {
        items(50) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                shape = RoundedCornerShape(8.dp),
                elevation = 5.dp
            ) {
                Box(
                    modifier = Modifier.height(100.dp)
                ) {}
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SearchGridScreen(){
    LazyVerticalGrid(
        cells = GridCells.Fixed(2),
        modifier = Modifier
            .fillMaxWidth(),
        contentPadding = PaddingValues(16.dp)
    ){
        items(10){
            Card(
                modifier = Modifier
                    .padding(16.dp),
                shape = RoundedCornerShape(8.dp),
                elevation = 5.dp
            ) {
                Box(
                    modifier = Modifier.height(200.dp)
                ) {}
            }

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
        SearchGridScreen()
    }
}