package com.example.intership_scrapproject_android.presentation.post.components

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.intership_scrapproject_android.core.util.OrderType
import com.example.intership_scrapproject_android.data.local.Post

@ExperimentalMaterialApi
@ExperimentalFoundationApi
@Composable
fun PostLazyColumn(
    navController: NavController,
    orderType: OrderType,
    posts: List<Post>,
    dismissItem : (Post) -> Unit,
) {

    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        if (orderType == OrderType.KEYWORD) {
            val grouped = posts.groupBy { it.keyword }
            grouped.forEach { (keyword, sectionKeyword) ->
                stickyHeader {
                    PostKeywordHeader(keyword)
                }
                items(
                    items = sectionKeyword,
                    itemContent = { post ->
                        PostItem(
                            modifier = Modifier.clickable{
                                navController.currentBackStackEntry?.savedStateHandle?.set("post",post)
                                navController.navigate("postDetail")
                            },
                            orderType = orderType,
                            post = post
                        )
                    }
                )
            }
        }
        else{
            items(posts) { post ->
                val dismissState = rememberDismissState()
                if (dismissState.isDismissed(DismissDirection.EndToStart)){
                    dismissItem(post)
                }
                SwipeToDismiss(
                    state = dismissState,
                    modifier = Modifier.padding(vertical = 4.dp),
                    directions = setOf(DismissDirection.EndToStart),
                    background = {
                        Box(
                            Modifier.fillMaxSize().background(Color.Red).padding(horizontal = 20.dp),
                            contentAlignment = Alignment.CenterEnd
                        ) {
                            Icon(
                                Icons.Default.Delete,
                                contentDescription = "Localized description",
                                modifier = Modifier.scale(1f)
                            )
                        }
                    },
                    dismissContent = {
                        Card(
                            elevation = animateDpAsState(
                                if (dismissState.dismissDirection != null) 4.dp else 0.dp
                            ).value
                        ) {
                            PostItem(
                                modifier = Modifier.clickable{
                                    navController.currentBackStackEntry?.savedStateHandle?.set("post",post)
                                    navController.navigate("postDetail")
                                },
                                orderType = orderType,
                                post = post
                            )
                        }
                    }
                )

            }
        }
    }
}