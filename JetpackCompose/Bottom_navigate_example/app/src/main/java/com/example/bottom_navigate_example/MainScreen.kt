package com.example.bottom_navigate_example

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController

@Composable
fun MainScreen(){
    val navController = rememberNavController()
    Scaffold (
        bottomBar = { BottomBar(navController = navController)}
    ) {
        BottomNavGraph(navController = navController)
    }

}

@Composable
fun BottomBar(navController: NavHostController){
    val screens = listOf(
        BottomBarScreen.Home,
        BottomBarScreen.Profile,
        BottomBarScreen.Settings
    )
    //Backstack을 지켜보고있다가 value가 바뀌면 자동으로 recompose해주는 state로 선언
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination //null일수도 있음

    BottomNavigation {
        screens.forEach{ screen ->
            addItem(
                screen = screen,
                currentDestination = currentDestination,
                navController = navController
            )
        }
    }
}

@Composable
fun RowScope.addItem(
    screen : BottomBarScreen, //sealed class로 이름들이 정의되어 있음
    currentDestination : NavDestination?,
    navController : NavHostController
){
    BottomNavigationItem(
        label = { Text(text = screen.title)},
        icon = {Icon(imageVector = screen.icon,contentDescription = "Navigation Icon")},
        selected = currentDestination?.hierarchy?.any{//현재 경로
            it.route == screen.route
        } == true,
        //선택 안된거 색깔 지정
        unselectedContentColor = LocalContentColor.current.copy(alpha = ContentAlpha.disabled),
        onClick = {
            //버튼 클릭시 마다 backstack에 쌓임
            //navController.navigate(screen.route)
            navController.navigate(screen.route){
                popUpTo(navController.graph.findStartDestination().id)
                launchSingleTop = true //multiple copy를 방지해줌
            }
        })
}