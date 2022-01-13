package com.example.my_first.composable

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState


@Composable
fun BottomBar(navController: NavHostController){
    val screens = listOf(
        BottomBarScreen.Search,
        BottomBarScreen.Scrap
    )
    //Backstack을 지켜보고있다가 value가 바뀌면 자동으로 recompose해주는 state로 선언
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination //null일수도 있음

    //material에 있는 컴포저블
    BottomNavigation {
        screens.forEach{ screen ->
            addItem(
                screen = screen,
                currentDestination = currentDestination, //현재 경로가 바뀌면 자동 update
                navController = navController
            )
        }
    }
}

@Composable
fun RowScope.addItem(
    screen : BottomBarScreen,
    currentDestination : NavDestination?,
    navController : NavHostController
) {
    BottomNavigationItem(
        label = { Text(text = screen.title) },
        icon = {
            Icon(imageVector = screen.icon, contentDescription = "navigation icon")
        },
        selected = currentDestination?.hierarchy?.any {//현재 경로
            it.route == screen.route
        } == true,
        onClick = {
            navController.navigate(screen.route) {
                popUpTo(navController.graph.findStartDestination().id)//뒤로가기 하면 Home screen이 나옴
                launchSingleTop = true //multiple copy를 방지해줌
            }
        })
}