package com.example.hellodagger

import android.util.Log
import javax.inject.Inject
import javax.inject.Provider

class ProviderTest {
    @Inject
    lateinit var provider : Provider<Any>

    fun print() {
        Log.d("Dagger","printing...")
        Log.d("Dagger","provider = ${provider.get()}")//호출시마다 새로운 객체 요청
        Log.d("Dagger","provider = ${provider.get()}")//동일 인스턴스 유지
        Log.d("Dagger","provider = ${provider.get()}")
    }
}