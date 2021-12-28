package com.example.basicsyntax

import android.content.Context
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
        Log.d("Collection", "Set 전체 출력 = ${set}")

        //5. 반복문
        for (index in 0..102 step 3) {
            Log.d("For", "현재숫자는 ${index}입니다")//0부터 100까지 3씩 증가
        }
        for (index in 10 downTo 0){
            Log.d("For","현재 숫자는 ${index}")//10부터 0까지
        }

        //6. 함수
        printPi()
        newFunction("Hello")
        newFunction("Hello", weight = 67.5)

        //7. 클래스
        Person("hyejin")
        var person = Person("hyejin",14)

        Pig.printName()
        val cutePig = Pig()
        cutePig.walk()
        //cutePig.printName() //error

        data class UserData(val name : String, var age : Int) // 이 한줄로 정의 가능
        var userData = UserData("michael", 21) // 일반 class처럼 생성가능
        //userData.name = "Sindy" // val이기 때문에 수정 불가능
        userData.age = 18 //var은 수정가능
        Log.d("dataclass" , "userData는 ${userData.toString()}, ${userData} ")
        Log.d("dataclass" , "person는 ${person.toString()} , ${person}")

        var customView = CustomView( getApplication() )

        //inheritence
        var child = Child()
        Log.d("inheritance","자식 입니다.${child.hello}",)
        child.sayHello()
        child.myHello()
        Log.d("inheritance","myhello 실행 후 ${child.hello}",)

        //익스텐션
        var myClass = MyClass()
        myClass.sleep()

        //10. 스코프 함수
        var list = mutableListOf("scope", "function")
        list.run {
            val listSize = size //this.size대신에 this를 생략한채로 사용가능
            Log.d("scope","리스트의 길이 run = $listSize")
        }


    }

    //6. 함수
    fun printPi() {
        Log.d("fun", "pi는 ${PI}입니다.")
    }

    companion object {
        const val PI = 3.14
    }

    fun newFunction(name : String, age : Int = 29, weight : Double = 65.5) {
        Log.d("fun", "name = ${name} , age = ${age}, weight =  ${weight}입니다")
    }


}