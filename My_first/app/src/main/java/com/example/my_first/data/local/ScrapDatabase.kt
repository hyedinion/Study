package com.example.my_first.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.my_first.domain.model.Scrap

@Database(
    entities = [Scrap::class],
    version = 1
)
abstract class ScrapDatabase : RoomDatabase() {
    abstract val scrapDao : ScrapDao
}