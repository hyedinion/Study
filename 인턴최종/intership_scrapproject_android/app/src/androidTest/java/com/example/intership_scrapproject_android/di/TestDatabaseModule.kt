package com.example.intership_scrapproject_android.di

import android.app.Application
import androidx.room.Room
import com.example.intership_scrapproject_android.data.local.PostDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import javax.inject.Singleton

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [DatabaseModule::class]
)
class TestDatabaseModule {

    @Provides
    @Singleton
    fun provideTestScrapDatabase(app: Application): PostDatabase {
        return Room.inMemoryDatabaseBuilder(
            app,
            PostDatabase::class.java
        ).build()
    }
}