package com.example.gallery_dagger_android.di.app

import com.example.gallery_dagger_android.MyApplication
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidSupportInjectionModule::class, ApplicationModule::class])
interface ApplicationComponent : AndroidInjector<MyApplication>{ //myApplication에 inject하겠다.
    @Component.Factory
    interface Factory : AndroidInjector.Factory<MyApplication>
}