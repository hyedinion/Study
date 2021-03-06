package com.example.intership_scrapproject_android.presentation.post

import com.example.intership_scrapproject_android.core.util.OrderType
import com.example.intership_scrapproject_android.data.local.Post
import com.example.intership_scrapproject_android.data.remote.response.BlogSearchItem
import com.example.intership_scrapproject_android.presentation.blog_search.BlogSearchEvent
import com.example.intership_scrapproject_android.presentation.post_detail.PostDetailEvent

sealed class PostEvent {
    data class OrderChange(val orderType: OrderType): PostEvent() // order가 변경되면 viewmodel에게 event 전송
    object ToggleOrderSection : PostEvent() //order section 버튼이 눌리면 event 전송
    object InitPostItem : PostEvent() //order section 버튼이 눌리면 event 전송
    data class DeletePost(val post : Post) : PostEvent()
    object ShowDeleteErrorToastHandled : PostEvent()
    object deletePostHandled : PostEvent()
}