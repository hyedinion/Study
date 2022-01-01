package com.example.thread_example

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var thread = WorkerThread()
        thread.start()

        var thread2 = Thread(WorkerRunnable())
        thread2.start()

        thread(start=true){
            var i = 0
            while(i<10){
                i+=1
                Log.d("worker","kotlinthread ${i}")
            }
        }
    }
}