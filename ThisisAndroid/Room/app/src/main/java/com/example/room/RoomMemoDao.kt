package com.example.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query

@Dao
interface RoomMemoDao {
    @Query("select * from room_memo")//다른 ORM과는 다른게 select쿼리는 직접 작성하도록 설계되어 있음
    fun getAll() : List<RoomMemo>

    @Insert(onConflict = REPLACE)//동일한 키를 가진 값이 입력되었을 때 update쿼리로 실행됨
    fun insert(memo : RoomMemo)

    @Delete
    fun delete(memo : RoomMemo)
}