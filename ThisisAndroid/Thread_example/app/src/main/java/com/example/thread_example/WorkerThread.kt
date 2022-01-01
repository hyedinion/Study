package com.example.thread_example

import android.util.Log

class WorkerThread : Thread() {
    override fun run() {//thread가 처리할 로직을 정의
        var i = 0
        while (i<10){
            i +=1
            Log.i("worker","thread $i")
        }
    }
}