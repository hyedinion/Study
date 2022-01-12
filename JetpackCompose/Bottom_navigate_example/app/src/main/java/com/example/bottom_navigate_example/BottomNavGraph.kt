package com.example.bottom_navigate_example

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.bottom_navigate_example.screens.Screen

@Composable
fun BottomNavGraph(navController : NavHostController){
    NavHost(
        navController = navController,
        startDestination = BottomBarScreen.Home.route
    ){
        composable(route = BottomBarScreen.Home.route){
            Screen(text = "Home", color = Color.Red)
        }
        composable(route = BottomBarScreen.Profile.route){
            Screen(text = "Profile", color = Color.Blue)
        }
        composable(route = BottomBarScreen.Settings.route){
            Screen(text = "Settings", color = Color.Green)
        }
    }
}