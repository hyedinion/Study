package com.example.gallery_dagger.di.fragment

import dagger.Component
import dagger.Subcomponent

@Component(modules = [ImageModule::class])
interface ImageComponent {
    fun inject(fragment : ImageFragment)

    @Subcomponent.Factory
    interface Factory{
        fun create() : ImageComponent
    }
}