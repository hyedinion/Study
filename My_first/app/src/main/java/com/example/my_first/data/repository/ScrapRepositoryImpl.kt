package com.example.my_first.data.repository

import com.example.my_first.data.local.ScrapDao
import com.example.my_first.domain.model.Scrap
import com.example.my_first.domain.repository.ScrapRepository
import kotlinx.coroutines.flow.Flow

class ScrapRepositoryImpl(
    private val dao : ScrapDao
) : ScrapRepository {
    override fun getScrap(): Flow<List<Scrap>> {
        return dao.getScrap()
    }

    override suspend fun getScrapById(id: Int): Scrap? {
        return dao.getScrapById(id)
    }

    override suspend fun insertScrap(scrap: Scrap) {
        dao.insertScrap(scrap)
    }

    override suspend fun deleteScrap(scrap: Scrap) {
        dao.deleteScrap(scrap)
    }
}