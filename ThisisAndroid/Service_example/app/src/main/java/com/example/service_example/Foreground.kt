package com.example.service_example

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.Build
import android.os.IBinder
import androidx.core.app.NotificationCompat

class Foreground : Service() {
    override fun onBind(intent: Intent): IBinder {
        return Binder()
    }

    //상태바에 알림을 띄울 채널을 설정할 때 사용
    val CHANNEL_ID = "ForegroundChannel"
    //알림 채널을 생성하는 메서드, 오레오 버전부터 모든 알림은 채널 단위로 동작
    fun createNotificationChannel(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val serviceChannel = NotificationChannel(
                CHANNEL_ID,
                "Foreground Service Channel",
                NotificationManager.IMPORTANCE_DEFAULT)
            val manager = getSystemService(NotificationManager::class.java)
            manager.createNotificationChannel(serviceChannel)
        }
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        createNotificationChannel() //알림 채널 생성
        val notification : Notification = NotificationCompat.Builder(this,CHANNEL_ID)
            .setContentTitle("Foreground Service")
            .setSmallIcon(R.mipmap.ic_launcher_round)
            .build() //알림 생성
        startForeground(1,notification)//서비스가 포어그라운드로 실행되고 있음을 안드로이드에게 알려줌
        return super.onStartCommand(intent, flags, startId)
    }
}