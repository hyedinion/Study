package com.example.gallery_dagger

import android.app.Application
import com.example.gallery_dagger.di.app.ApplicationComponent

class MyApplication : Application() {
    companion object{
        //applicationcomponent를 application 범위에서 참조가능하도록 만들어 줌
        lateinit var appComponent : ApplicationComponent
    }

    override fun onCreate() {
        super.onCreate()
        //application에서 해당 applicationComponent 생성
        appComponent = DaggerApplicationComponent.factory().create(this)
    }
}