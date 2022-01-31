package com.example.intership_scrapproject_android.di

import android.app.Application
import androidx.room.Room
import com.example.intership_scrapproject_android.data.local.PostDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    @Singleton
    fun provideScrapDatabase(app: Application): PostDatabase{
        return Room.databaseBuilder(
            app,
            PostDatabase::class.java,
            "post_db"
        ).build()
    }
}