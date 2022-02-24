package com.example.flowtest

import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    private val list = mutableListOf<String>()
    val liveDataList =


    fun addList() {
        list.add("hi")
    }

    fun deleteList() {
        list.removeAt(0)
    }

}