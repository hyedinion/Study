package com.example.timer_example

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import com.example.timer_example.databinding.ActivityMainBinding
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {
    val binding by lazy {ActivityMainBinding.inflate(layoutInflater)}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        var total = 0
        var started = false

        val handler = object : Handler(Looper.getMainLooper()){
            override fun handleMessage(msg: Message) {
                val minute = String.format("%02d",total/60)
                val second = String.format("%02d",total%60)
                binding.textTimer.text = "$minute:$second"
            }
        }

        binding.btnStart.setOnClickListener {
            started = true
            thread(start=true) {
                while(started){
                    Thread.sleep(1000)//1초에 1씩 total 증가시킴
                    if (started){
                        total = total+1
                        handler?.sendEmptyMessage(0) //핸들러에게 메세지 전달, 핸들러는 메세지 받으면 handleMessage함수 수행
                    }
                }
            }
        }

        binding.btnStop.setOnClickListener {
            if(started){
                started = false
                total = 0
                binding.textTimer.text = "00:00"
            }
        }
    }
}