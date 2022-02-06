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

@Composable
fun BlogSearchList(
    result: LazyPagingItems<BlogSearchItem>?,
    navController: NavController,
    keyword : String,
){
    val item = BlogSearchItem(
        bloggerlink = "",
        bloggername = "민폐스러운 일상",
        title = "Happy New Year & Thanks",
        description = "(아까워서 못 까겠다.) Happy New Year, 라고 적기 전에 잡소리를 너무 길게 남긴 것 같아 좀 부끄럽다. 보기 싫은데 새 글 떠서 억지로 보게 될 이웃님들께는 그저 죄송할 따름이지만... 그냥 길고 힘들었던 2021년을... ",
        link = "link",
        postdate = "20211231",
    )
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .testTag("blogSearchLazyColumn"),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)//item간의 간격
    ) {
        if (result != null) {
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