package com.example.gallery_dagger.di.app

import android.app.Application
import android.content.Context
import com.example.gallery_dagger.di.activity.GalleryComponent
import dagger.Binds
import dagger.Module

//Gallery 컴포넌트를 Application컴포넌트의 서브 컴포넌트로 구성
@Module(subcomponents = [GalleryComponent::class])
abstract class ApplicationModule {
    @Binds
    abstract  fun bindsContext(application: Application):Context
}