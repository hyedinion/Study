package com.example.intership_scrapproject_android.presentation.post.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.intership_scrapproject_android.core.util.OrderType
import com.example.intership_scrapproject_android.data.local.Post

@ExperimentalFoundationApi
@Composable
fun PostLazyColumn(
    navController: NavController,
    orderType: OrderType,
    posts: List<Post>
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
    }
}