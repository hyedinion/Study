package com.example.intership_scrapproject_android.presentation.main

import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.NavHostController
import com.example.intership_scrapproject_android.presentation.main.bottom_bar.BottomBarRoute
import com.example.my_first.presentation.Search.BlogSearchScreen

@ExperimentalComposeUiApi
@Composable
fun ScrapNavHost(navController : NavHostController) {
    NavHost(
    navController = navController,
    startDestination = BottomBarRoute.BlogSearch.route
    ){
        composable(route = BottomBarRoute.BlogSearch.route){
            BlogSearchScreen(navController = navController)
        }

    }
}