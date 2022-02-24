package com.example.intership_scrapproject_android.presentation.main.bottom_bar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.TurnedIn
import androidx.compose.material.icons.filled.TurnedInNot
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomBarRoute(
    val route : String,
    val title : String,
    val icon : ImageVector
){
    object BlogSearch : BottomBarRoute(
        route = "blog_search",
        title = "검색",
        icon = Icons.Default.Search
    )
    object Post : BottomBarRoute(
        route = "post",
        title = "스크랩",
        icon = Icons.Default.TurnedInNot
    )
}