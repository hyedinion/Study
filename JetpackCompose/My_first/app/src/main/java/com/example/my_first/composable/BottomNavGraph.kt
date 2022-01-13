package com.example.my_first.composable

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.BlendMode.Companion.Screen
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.my_first.composable.screen.SearchGridScreen
import com.example.my_first.composable.screen.SearchListScreen

@Composable
fun BottomNavGraph(navController : NavHostController){
    NavHost(
        navController = navController,
        startDestination = BottomBarScreen.Search.route
    ){
        composable(route = BottomBarScreen.Search.route){
            SearchListScreen()
        }
        composable(route = BottomBarScreen.Scrap.route){
            SearchGridScreen()
        }
    }
}