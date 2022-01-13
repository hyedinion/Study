package com.example.my_first.composable.screen
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.my_first.composable.BottomBar
import com.example.my_first.composable.BottomBarScreen
import com.example.my_first.composable.BottomNavGraph
import com.example.my_first.composable.SearchAppBar
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import kotlinx.coroutines.delay

@Composable
fun MainScreen(){
    val navController = rememberNavController()
    Scaffold (
        topBar = { SearchAppBar(
            text = "",
            onTextChange = {},
            onCloseClicked = {},
            onSearchClicked = {}
        )},
        bottomBar = { BottomBar(navController = navController)}
    ) {


        var refreshing by remember {mutableStateOf(false)}
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
            BottomNavGraph(navController = navController)
        }

    }

}
