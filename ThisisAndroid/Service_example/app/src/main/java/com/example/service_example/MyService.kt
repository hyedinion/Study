package com.example.service_example

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import android.util.Log

class MyService : Service() {
    inner class MyBinder : Binder(){//바인더 클래스
        fun getService() : MyService{
            return this@MyService
        }
    }//액티비티와 서비스가 연결되면 바인더의 getService() 메서드를 통해 서비스에 접근할 수 있음
    val binder = MyBinder()

    override fun onBind(intent: Intent): IBinder {
        return binder
    }//바운드 서비스를 위한 onBind 메서드, 스타티드 서비스에서는 사용하지 않음

    //서비스의 메서드 호출하기
    fun serviceMessage() : String{
        return "Hello Activity! i am service"
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val action = intent?.action
        Log.d("서비스", "action = $action")
        return super.onStartCommand(intent, flags, startId)
    }//서비스 호출시 명령어 전달 가능
    companion object{ // 명령어 모음 (패키지명 + 명령어)
        val ACTION_START = "com.example.service_example.START"
        val ACTION_RUN = "com.example.service_example.RUN"
        val ACTION_STOP = "com.example.service_example.STOP"
    }
    override fun onDestroy() {
        Log.d("서비스", "서비스가 종료되었습니다.")
        super.onDestroy()
    }//서비스 종료시 호출
}