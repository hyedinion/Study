package com.example.my_first.domain.util

sealed class OrderType{
    object Ascending : OrderType()
    object Descending : OrderType()
}