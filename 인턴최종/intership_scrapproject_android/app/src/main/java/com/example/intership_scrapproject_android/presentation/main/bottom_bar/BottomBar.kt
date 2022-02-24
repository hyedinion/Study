package com.example.intership_scrapproject_android.presentation.main.bottom_bar

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.intership_scrapproject_android.util.TestTags
import androidx.compose.ui.Modifier


@Composable
fun BottomBar(navController: NavHostController){
    val screens = listOf(
        BottomBarRoute.BlogSearch,
        BottomBarRoute.Post
    )

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    BottomNavigation(
        backgroundColor = MaterialTheme.colors.onSurface,
    ) {
        screens.forEach{ screen ->
            addItem(
                route = screen,
                currentDestination = currentDestination,
                navController = navController
            )
        }
    }
}

@Composable
fun RowScope.addItem(
    route : BottomBarRoute,
    currentDestination : NavDestination?,
    navController : NavHostController,
) {
    var testString = TestTags.blogSearchBottomButton
    if (route == BottomBarRoute.Post){
        testString = TestTags.postBottomButton
    }
    BottomNavigationItem(
        modifier = Modifier.testTag(testString),
        label = {
            Text(text = route.title)
        },
        icon = {
            Icon(imageVector = route.icon, contentDescription = "navigation icon")
        },
        selected = currentDestination?.hierarchy?.any {
            it.route == route.route
        } == true,
        onClick = {
            navController.navigate(route.route) {
                popUpTo(navController.graph.findStartDestination().id)
                launchSingleTop = true
            }
        },
        selectedContentColor = MaterialTheme.colors.primary,
        unselectedContentColor = Color.DarkGray
    )
}