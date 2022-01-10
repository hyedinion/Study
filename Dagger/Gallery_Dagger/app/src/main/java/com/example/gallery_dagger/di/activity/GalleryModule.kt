package com.example.gallery_dagger.di.activity
import com.example.gallery_dagger.di.fragment.ImageComponent
import dagger.Module

@Module(subcomponents = [ImageComponent::class])
class GalleryModule {
}