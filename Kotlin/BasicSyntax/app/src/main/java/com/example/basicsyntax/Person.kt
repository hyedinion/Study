package com.example.basicsyntax

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.View

//class
class Person(value : String) {
    init {
        Log.d("class", "생성자로부터 전달받은 값은 ${value} 입니다.")
    }
    constructor(value : String, value2 : Int) : this(value) {
        Log.d("class", "생성자로부터 전달받은 값은 ${value2}입니다.")
    }
}

// companion object
class Pig {
    companion object {
        var name : String = "None"
        fun printName() {
            Log.d("class", "Pig의 이름은 ${name}입니다.")
        }
    }
    fun walk(){
        Log.d("class", "Pig가 걸어갑니다.")
    }
}

//부모 클래스 세컨더리 생성
class CustomView : View { // 부모 클래스명 다음 괄호를 생략
    constructor(ctx : Context) : super(ctx){
        Log.d("class", "부모의 세컨더리 생성자")
    }
    constructor(ctx : Context, attrs : AttributeSet) : super(ctx,attrs)
}

//상속
open class Parent{
    var hello : String = "hi"
    fun sayHello(){
        Log.d("inheritance","${hello}")
    }
}

class Child : Parent(){
    fun myHello(){
        hello = "hello"
        sayHello()
    }
}

//익스텐션
class MyClass(){
    fun say(){Log.d("extention","말하기 시작")}
    fun walk(){Log.d("extention","걷기 시작")}
    fun eat(){Log.d("extention","먹기 시작")}
}
//여기에 추가로 sleep 메서드를 추가하고 싶다!
//그럴 때 익스텐션 사용가능
fun MyClass.sleep(){
    Log.d("extention","잠자기 시작")
}