package com.example.hellodagger

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //component생성
        val myComponent : MyComponent = DaggerMyComponent.create()

        //프로비젼 메서드
        val str = myComponent.getHelloWorld()
        Log.d("Dagger","${str}입니다.")

        //멤버 인젝션 메서드
        val gretting = Gretting()
        myComponent.inject(gretting)
        gretting.say()

        //생성자 주입
        val student = myComponent.getStudent()
        Log.d("Dagger", "${student.name} 학생이름 입니다.")

        //Lazy<>
        val component : ObjectComponent = DaggerObjectComponent.create()
        var lazytest = LazyTest()
        component.inject(lazytest)
        lazytest.print()

        //Provide<>
        var provide = ProviderTest()
        component.inject(provide)
        provide.print()

    }
}