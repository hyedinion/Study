package com.example.hellodagger

import android.util.Log
import dagger.Lazy
import javax.inject.Inject

class LazyTest {
    @Inject
    lateinit var lazy : Lazy<Any>

    fun print() {
        Log.d("Dagger","printing...")
        Log.d("Dagger","lazy = ${lazy.get()}")//여기서 객체 초기화
        Log.d("Dagger","lazy = ${lazy.get()}")//동일 인스턴스 유지
        Log.d("Dagger","lazy = ${lazy.get()}")
    }


}