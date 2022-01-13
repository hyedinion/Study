package com.example.component_test

import android.content.Context

class ResourceComparer {

    //context로부터 가져온 String resource가 원하는 String값과 같은지 확인
    fun isEqual(context: Context, resId : Int, string: String) : Boolean{
        return context.getString(resId) == string
    }
}