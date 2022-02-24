package com.example.intership_scrapproject_android.di


import com.example.intership_scrapproject_android.data.remote.BlogSearchAPI
import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import org.junit.Rule
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule
import javax.inject.Singleton

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [ApiModule::class]
)
class TestApiModule {

    @get:Rule
    var rule: MockitoRule = MockitoJUnit.rule()

    @Singleton
    @Provides
    fun provideTestSearchAPI() : BlogSearchAPI {
        return Mockito.mock(BlogSearchAPI::class.java)
    }
}