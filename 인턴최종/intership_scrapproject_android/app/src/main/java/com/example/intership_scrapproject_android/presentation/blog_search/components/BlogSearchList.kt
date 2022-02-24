package com.example.intership_scrapproject_android.presentation.blog_search.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.paging.compose.LazyPagingItems
import com.example.intership_scrapproject_android.data.remote.response.BlogSearchItem
import com.example.intership_scrapproject_android.presentation.main.NavHostRoute
import com.example.intership_scrapproject_android.util.TestTags

@Composable
fun BlogSearchList(
    result: LazyPagingItems<BlogSearchItem>?,
    navController: NavController,
    keyword : String,
){
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .testTag(TestTags.blogSearchLazyColumn),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)//item간의 간격
    ) {
        if (result != null) {
            items(result.itemCount) { i ->
                BlogSearchItemBox(
                    modifier = Modifier
                        .clickable{
                            navController.currentBackStackEntry?.savedStateHandle?.set("blog",result[i])
                            navController.currentBackStackEntry?.savedStateHandle?.set("keyword",keyword)
                            navController.navigate(NavHostRoute.blogSearchDetail)
                        }
                        .testTag(TestTags.blogSearchListItemBox)
                    ,
                    item = result[i]!!,
                    scrapped = result[i]!!.linkExist
                )
            }
        }
    }
}