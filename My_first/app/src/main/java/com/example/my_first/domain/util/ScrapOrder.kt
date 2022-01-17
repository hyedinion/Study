package com.example.my_first.domain.util

sealed class ScrapOrder (val orderType: OrderType){
    class Keyword(orderType: OrderType) : ScrapOrder(orderType)
    class PostDate(orderType: OrderType) : ScrapOrder(orderType)
    class ScrapDate(orderType: OrderType) : ScrapOrder(orderType)

    fun copy(orderType: OrderType): ScrapOrder{
        return when(this){
            is Keyword -> Keyword(orderType)
            is PostDate -> PostDate(orderType)
            is ScrapDate -> ScrapDate(orderType)
        }
    }
}
