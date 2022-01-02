package com.example.service_example

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.example.service_example.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    val binding by lazy {ActivityMainBinding.inflate(layoutInflater)}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btnStart.setOnClickListener {
            val intent = Intent(this,Foreground::class.java)
            ContextCompat.startForegroundService(this,intent)//포어그라운드는 ContextCompat으로 시작해야함
        }
        binding.btnStop.setOnClickListener {
            val intent = Intent(this, Foreground::class.java)
            stopService(intent)
        }

    }



    var myService:MyService? = null
    var isService = false
    val connection = object : ServiceConnection{
        override fun onServiceConnected(p0: ComponentName?, p1: IBinder?) {//p0 : name , p1 : service
            val binder = p1 as MyService.MyBinder
            myService = binder.getService()
            isService = true
            Log.d("서비스","연결되었습니다.")
        }//정상적으로 서비스가 연결되면 호출됨
        override fun onServiceDisconnected(p0: ComponentName?) {
            isService = false
        }//비정상적으로 서비스 연결이 해제되었을 때만 호출됨. 그래서 연결중인지 아닌지는 isService를 통해 확인해야함
    }

    //서비스의 메서드 호출하기
    fun callServiceFunction(view : View){
        if(isService){
            val message = myService?.serviceMessage()
            Toast.makeText(this, "message = ${message}",Toast.LENGTH_LONG).show()
        }else{
            Toast.makeText(this, "서비스가 연결되지 않았습니다.",Toast.LENGTH_LONG).show()
        }
    }

    fun serviceBind(view : View){
        val intent = Intent(this, MyService::class.java)
        bindService(intent,connection,Context.BIND_AUTO_CREATE)//위의 커넥션을 같이 넘겨줘야함, BIND_AUTO_CREATE : 서비스가 생성되어 있지 않으면 생성 후 바인딩
    }

    fun serviceUnbind(view : View){
        if (isService){
            unbindService(connection)
            isService = false
        }
    }

    fun serviceStart(view : View){
        val intent = Intent(this,MyService::class.java)
        intent.action = MyService.ACTION_START
        startService(intent)
        val intent2 = Intent(this,MyService::class.java)
        intent2.action = MyService.ACTION_START
        startService(intent2)
    }//액티비티에서 동일한 인텐트를 생성하고 startService() 해도 서비스는 더이상 생성되지 않고 OnStartCommand()만 호출됨

    fun serviceStop(view : View){
        val intent = Intent(this,MyService::class.java)
        stopService(intent)
    }
}