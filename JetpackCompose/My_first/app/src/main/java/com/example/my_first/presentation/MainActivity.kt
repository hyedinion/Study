package com.example.my_first.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.my_first.presentation.Search.SearchScreen
import com.example.my_first.presentation.add_scrap.components.EditScrapScreen
import com.example.my_first.presentation.scrap.components.ScrapScreen
import com.example.my_first.ui.theme.My_firstTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @ExperimentalAnimationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            My_firstTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = BottomBarScreen.Search.route
                ){
                    composable(route = BottomBarScreen.Search.route){
                        SearchScreen(navController = navController)
                    }
                    composable(route = "add_scrap"){
                        EditScrapScreen()
                    }
                    composable(route = BottomBarScreen.Scrap.route){
                        ScrapScreen(navController = navController)
                    }
                }

            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    My_firstTheme {

    }
}