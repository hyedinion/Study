package com.example.intership_scrapproject_android

import java.util.*
import kotlin.concurrent.schedule

object AsyncTimer {
    var expired = false
    fun start(delay: Long = 1000){
        expired = false
        Timer().schedule(delay) {
            expired = true
        }
    }
}