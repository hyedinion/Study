package com.example.my_first.di

import android.app.Application
import androidx.room.Room
import com.example.my_first.data.local.ScrapDatabase
import com.example.my_first.data.remote.SearchAPI
import com.example.my_first.data.remote.SearchRepository
import com.example.my_first.data.repository.ScrapRepositoryImpl
import com.example.my_first.domain.repository.ScrapRepository
import com.example.my_first.domain.use_case.DeleteScrap
import com.example.my_first.domain.use_case.GetScraps
import com.example.my_first.domain.use_case.ScrapUseCases
import com.example.my_first.util.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
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

    @Singleton
    @Provides
    fun provideSearchAPI() : SearchAPI{
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(SearchAPI::class.java)
    }

    @Provides
    @Singleton
    fun provideScrapRepository(db : ScrapDatabase) : ScrapRepository{
        return ScrapRepositoryImpl(db.scrapDao)
    }

    @Provides
    @Singleton
    fun provideSearchRepository(api : SearchAPI) : SearchRepository{
        return SearchRepository(api)
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