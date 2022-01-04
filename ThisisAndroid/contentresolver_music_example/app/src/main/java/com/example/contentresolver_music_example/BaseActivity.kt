package com.example.contentresolver_music_example

import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat

abstract class BaseActivity : AppCompatActivity() {
    abstract fun permissionGranted(requestCode : Int)
    abstract fun permissionDenied(reauestCode : Int)

    //권한 호출시 직접 호출하는 메서드
    fun requirePermissions(permissions : Array<String>, requestCode : Int){
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {//마시멜로 이하 버전이면 승인과정 필요없음
            permissionGranted(requestCode)
        }else{
            val isAllPermissionsGranted = permissions.all{
                checkSelfPermission(it) == PackageManager.PERMISSION_GRANTED
            } // all 메서드를 사용하면 배열 속에 들어있는 모든 값을 체크할 수 있음, 모두 true면 true를 반환
            if (isAllPermissionsGranted) {
                permissionGranted(requestCode)
            }else{
                ActivityCompat.requestPermissions(this,permissions,requestCode)
            }//사용자에게 권한 요청
        }
    }

    override fun onRequestPermissionsResult(//권한 요청 후 호출하는 메서드
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (grantResults.all { it == PackageManager.PERMISSION_GRANTED }){
            permissionGranted(requestCode)
        }else{
            permissionDenied(requestCode)
        }
    }
}