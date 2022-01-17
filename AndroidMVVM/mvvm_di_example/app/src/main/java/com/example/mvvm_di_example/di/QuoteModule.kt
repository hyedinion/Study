package com.example.mvvm_di_example.di

import com.example.mvvm_di_example.data.QuoteRepository
import com.example.mvvm_di_example.data.QuoteRepositoryImpl
import com.example.mvvm_di_example.data.db.Database
import com.example.mvvm_di_example.data.db.FakeDatabaseImpl
import com.example.mvvm_di_example.data.db.QuoteDao
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class QuoteModule {

    @Singleton
    @Provides
    fun provideDatabase(): Database = FakeDatabaseImpl()

    @Singleton
    @Provides
    fun provideDao(database : Database) : QuoteDao = database.quoteDao

    @Singleton
    @Provides
    fun provideQuoteRepository(quoteDao : QuoteDao) : QuoteRepository = QuoteRepositoryImpl(quoteDao)

}