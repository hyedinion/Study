package com.example.gallery_dagger.di.activity

import com.example.gallery_dagger.di.fragment.ImageComponent
import dagger.BindsInstance
import dagger.Subcomponent

@ActivityScope
@Subcomponent(modules = [GalleryModule::class])
interface GalleryComponent {

    //멤버-인젝션 메서드, 필드주입
    fun inject(activity:GalleryActivity)
    fun getImageComponentFactory() : ImageComponent.Factory

    @Subcomponent.Factory
    interface Factory{
        fun create(@BindsInstance activity:GalleryActivity) : GalleryComponent
    }
}