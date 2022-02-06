package com.example.intership_scrapproject_android.presentation.main

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.NavHostController
import com.example.intership_scrapproject_android.data.remote.response.BlogSearchItem
import com.example.intership_scrapproject_android.presentation.blog_search_detail.components.BlogDetailScreen
import com.example.intership_scrapproject_android.presentation.main.bottom_bar.BottomBarRoute
import com.example.intership_scrapproject_android.presentation.blog_search.components.BlogSearchScreen
import com.example.intership_scrapproject_android.presentation.blog_search_scrap.components.BlogScrapScreen
import com.example.intership_scrapproject_android.presentation.post.components.PostScreen


@ExperimentalComposeUiApi
@ExperimentalAnimationApi
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ScrapNavHost(navController : NavHostController) {
    NavHost(
    navController = navController,
    startDestination = BottomBarRoute.BlogSearch.route
    ){
        composable(route = BottomBarRoute.BlogSearch.route){
            BlogSearchScreen(navController = navController)
        }
        composable(route = BottomBarRoute.Post.route){
            PostScreen(navController = navController)
        }
        composable(
            route = "blogSearchDetail",
        ){
            BlogDetailScreen(
                navController = navController,
                keyword = navController.previousBackStackEntry?.savedStateHandle?.get<String>("keyword"),
                blog = navController.previousBackStackEntry?.savedStateHandle?.get<BlogSearchItem>("blog"),
            )
        }
        composable(route = "blogSearchScrap"){
            BlogScrapScreen(
                navController = navController,
                keyword = navController.previousBackStackEntry?.savedStateHandle?.get<String>("keyword"),
                blog = navController.previousBackStackEntry?.savedStateHandle?.get<BlogSearchItem>("blog"),
            )
        }
        composable(route = "postDetail"){
            BlogSearchScreen(navController = navController)
        }
        composable(route = "postEdit"){
            BlogSearchScreen(navController = navController)
        }
        composable(route = "postSearchScreen"){
            BlogSearchScreen(navController = navController)
        }

    }
}