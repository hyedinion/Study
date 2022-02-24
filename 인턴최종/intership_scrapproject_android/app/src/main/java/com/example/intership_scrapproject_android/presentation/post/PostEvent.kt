package com.example.intership_scrapproject_android.presentation.post

import com.example.intership_scrapproject_android.util.OrderType

sealed class PostEvent {
    data class OrderChange(val orderType: OrderType): PostEvent()
    object ToggleOrderSection : PostEvent()
    object InitPostItem : PostEvent()
}