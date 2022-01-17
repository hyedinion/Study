package com.example.my_first.domain.repository

import com.example.my_first.domain.model.Scrap
import kotlinx.coroutines.flow.Flow

interface ScrapRepository {

    fun getScrap() : Flow<List<Scrap>>

    suspend fun getScrapById(id : Int) : Scrap?

    suspend fun insertScrap(scrap: Scrap)

    suspend fun deleteScrap(scrap: Scrap)

}