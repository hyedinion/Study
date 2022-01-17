package com.example.my_first.di

import android.app.Application
import androidx.room.Room
import com.example.my_first.data.local.ScrapDatabase
import com.example.my_first.data.repository.ScrapRepositoryImpl
import com.example.my_first.domain.repository.ScrapRepository
import com.example.my_first.domain.use_case.DeleteScrap
import com.example.my_first.domain.use_case.GetScraps
import com.example.my_first.domain.use_case.ScrapUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideScrapDatabase(app: Application): ScrapDatabase{
        return Room.databaseBuilder(
            app,
            ScrapDatabase::class.java,
            "scrap_db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideScrapRepository(db : ScrapDatabase) : ScrapRepository{
        return ScrapRepositoryImpl(db.scrapDao)
    }

    @Provides
    @Singleton
    fun provideScrapUseCases(repository: ScrapRepository): ScrapUseCases{
        return ScrapUseCases(
            getScraps = GetScraps(repository),
            deleteScrap = DeleteScrap(repository)
        )
    }

}