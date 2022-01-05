package com.example.hellodagger

import dagger.Module
import dagger.Provides

@Module
class MyModule {
    @Provides
    fun provideHelloWorld() : String {return "hello world~"}
}