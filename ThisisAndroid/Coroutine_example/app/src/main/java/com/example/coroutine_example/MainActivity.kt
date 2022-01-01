package com.example.coroutine_example

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.coroutine_example.databinding.ActivityMainBinding
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {
    val binding by lazy{ ActivityMainBinding.inflate(layoutInflater)}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        GlobalScope.launch {
            //여기 코드가 코루틴으로 실행됨
        }

        //withContext 디스패처 분리
        suspend fun readFile(): String{
            return "파일내용"
        }
        CoroutineScope(Dispatchers.Main).launch {
            //화면 처리
            val result = withContext(Dispatchers.IO){
                readFile()
            }
            Log.d("코루틴", "파일 결과 = $result")
        }

        //suspend 코루틴의 장점
        suspend fun subRoutine(){
            for (i in 0..10){
                Log.d("코루틴", "suspend $i")
            }
        }
        CoroutineScope(Dispatchers.Main).launch {
            //코드1
            subRoutine() //코루틴에서 자동으로 백그라운드 스레드 처럼 동작
            //코드2
        }

        //async 반환값 처리
        CoroutineScope(Dispatchers.Default).async {
            val deferred1 = async{
                delay(5000)
                350
            }
            val deferred2 = async {
                delay(5500)
                200
            }
            Log.d("코루틴","연산결과 = ${deferred1.await()+deferred2.await()}")
        }

        //launch 상태 관리
        val job = CoroutineScope(Dispatchers.Default).launch {
            val job1 = launch {
                for (i in 1..10){
                    delay(500)
                    Log.d("코루틴","결과 = $i")
                }
            }.join() // job1이 끝나고 job2 실행
            val job2 = launch {
                for (i in 0..10){
                    delay(500)
                    Log.d("코루틴", "결과2 = $i")
                }
            }
        }

        binding.btnStop.setOnClickListener {
            job.cancel()// 코루틴의 동작을 멈춤
        }

    }
}