package com.example.intership_scrapproject_android.presentation.blog_search.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import com.example.intership_scrapproject_android.data.remote.response.BlogSearchItem

@Composable
fun BlogSearchList(
    result: LazyPagingItems<BlogSearchItem>?
){
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)//item간의 간격
    ) {
        if (result != null) {
            items(result.itemCount) { i ->
                BlogSearchItemBox(
                    item = result[i]!!,
                    scrapped = result[i]!!.linkExist
                )
            }
        }
    }
}