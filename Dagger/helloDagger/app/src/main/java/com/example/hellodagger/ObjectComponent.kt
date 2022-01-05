package com.example.hellodagger

import dagger.Component

@Component(modules = [ObjectModule::class])
interface ObjectComponent {
    fun inject(lazyTest : LazyTest)
    fun inject(providerTest : ProviderTest)
}