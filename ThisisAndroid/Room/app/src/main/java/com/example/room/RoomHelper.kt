package com.example.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = arrayOf(RoomMemo::class), version = 1, exportSchema = false)
//entities : Room라이브러리가 사용할 엔티티 클래스 목록, exportSchema : true면 스키마 정보를 파일로 출력
abstract class RoomHelper : RoomDatabase(){
    abstract fun roomMemoDao() : RoomMemoDao
}