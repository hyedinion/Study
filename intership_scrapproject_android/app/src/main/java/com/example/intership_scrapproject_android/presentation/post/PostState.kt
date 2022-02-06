package com.example.intership_scrapproject_android.presentation.post

import com.example.intership_scrapproject_android.core.util.OrderType
import com.example.intership_scrapproject_android.data.local.Post

data class PostState(
    val posts : List<Post> = emptyList(),
    val postOrder: OrderType = OrderType.SCRAP_DATE,
    val isOrderSectionVisible : Boolean = false,
    val initBlogScrapPostNeed : Boolean = true,
)
