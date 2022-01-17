package com.example.my_first.data.local

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.my_first.domain.model.Scrap
import kotlinx.coroutines.flow.Flow

@Dao
interface ScrapDao {

    //livedata는 suspend 안해줘도 됨
    @Query("SELECT * FROM scrap")
    fun getScrap() : Flow<List<Scrap>>

    @Query("SELECT * FROM scrap WHERE id = :id")//:는 파라미터로 넘겨준 변수라는 것을 알려줌
    suspend fun getScrapById(id : Int) : Scrap?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertScrap(scrap : Scrap)

    @Delete
    suspend fun deleteScrap(scrap : Scrap)

}