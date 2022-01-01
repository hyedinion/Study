package com.example.thread_example

import android.util.Log

class WorkerRunnable : Runnable {//Runnable interface임
    override fun run() {
        var i = 0
        while (i<10){
            i +=1
            Log.i("worker","runnable $i")
        }
    }
}