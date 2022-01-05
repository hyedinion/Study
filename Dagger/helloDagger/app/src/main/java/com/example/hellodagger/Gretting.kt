package com.example.hellodagger

import android.util.Log
import javax.inject.Inject

class Gretting {

    @Inject
    lateinit var message : String

    fun say() {
        Log.d("dagger inject","$message")
    }
}