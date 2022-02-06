package com.example.intership_scrapproject_android.presentation.blog_search.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.paging.compose.LazyPagingItems
import com.example.intership_scrapproject_android.data.remote.response.BlogSearchItem

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun BlogSearchGrid(
    result : LazyPagingItems<BlogSearchItem>?,
    navController: NavController,
    keyword : String,
){
    LazyVerticalGrid(
        cells = GridCells.Fixed(2),
        modifier = Modifier
            .fillMaxWidth(),
        contentPadding = PaddingValues(16.dp)
    ){
        if (result!=null) {
            items(result.itemCount) { i ->
                BlogSearchItemBox(
                    modifier = Modifier.clickable{
                        navController.currentBackStackEntry?.savedStateHandle?.set("blog",result[i])
                        navController.currentBackStackEntry?.savedStateHandle?.set("keyword",keyword)
                        navController.navigate("blogSearchDetail")
                    },
                    item = result[i]!!,
                    scrapped = result[i]!!.linkExist
                )
            }
        }
    }
}