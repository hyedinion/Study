package com.example.basicsyntax

import android.util.Log

class Person(value : String) {
    init {
        Log.d("class", "생성자로부터 전달받은 값은 ${value} 입니다.")
    }
    constructor(value : String, value2 : Int) : this(value) {
        Log.d("class", "생성자로부터 전달받은 값은 ${value2}입니다.")
    }
}

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