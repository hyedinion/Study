package com.example.gallery_dagger.di.app

import android.app.Application
import com.example.gallery_dagger.di.activity.GalleryComponent
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(modules = [ApplicationModule::class])
interface ApplicationComponent {
    @Component.Factory
    interface Factory{
        fun create(@BindsInstance application : Application) : ApplicationComponent
    } //application을 오브젝트 그래프에 바인딩 하고 있다.
    //엑티비티용 서브 컴포넌트
    fun getGalleryComponentFactory() : GalleryComponent.Factory
}