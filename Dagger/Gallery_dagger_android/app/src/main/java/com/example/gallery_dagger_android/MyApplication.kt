package com.example.gallery_dagger_android

import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class MyApplication : DaggerApplication(){
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        //applicationComponent 만들어서 반환
        return DaggerApplicationComponent.factory().create(this)
    }
}