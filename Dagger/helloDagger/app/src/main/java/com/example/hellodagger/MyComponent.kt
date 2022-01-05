package com.example.hellodagger

import dagger.Component

@Component(modules = [MyModule::class])
interface MyComponent {
    fun getHelloWorld() : String
    fun inject(gretting : Gretting)
    fun getStudent() : Student

    @Hello
    fun getCustomHello() : String
}