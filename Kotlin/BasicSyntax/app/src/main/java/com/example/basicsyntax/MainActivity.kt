package com.example.basicsyntax

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //1. 로그 출력
        Log.d("BasicSyntax", "로그를 출력합니다. method = Log.d")

        //2. 타입 출력
        var myName : String = "홍길동"
        var myAge : Int
        myAge = 27
        myAge = myAge + 1
        Log.d("BasicSyntax", "myName = $myName, myAge = $myAge")
        
        //4. 컬렉션
        var set = mutableSetOf<String>()
        set.add("JAN")
        set.add("FEB")
        set.add("JAN")
        Log.d("Collection", "Set 전체 출력 = $set")

    }
}