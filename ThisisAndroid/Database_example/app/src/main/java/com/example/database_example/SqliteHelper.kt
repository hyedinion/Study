package com.example.database_example

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class SqliteHelper(context : Context, name : String, version : Int) : SQLiteOpenHelper(context,name,null,version){
    override fun onCreate(p0: SQLiteDatabase?) {//p0 : database
        val create = "create table memo (" +
                "no integer primary key, " +
                "content text, " +
                "datetime integer" +
                ")"

        p0?.execSQL(create)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {//p0: database, p1: oldversion, p2: newversion
        //여기는 sqliteHelper에 전달되는 버전정보가 변경되었을 때 호출
    }

    fun insertMemo(memo : Memo){
        val values = ContentValues() //SQLiteOpenHelper를 이용해서 값을 입력할 땐 contentValues 클래스 사용해야됨 (Map같은거)
        values.put("content", memo.content) //put(컬럼명, 값)
        values.put("datetime",memo.datetime)
        val wd = writableDatabase //writableDatabase는 SQLiteOpenHelper에 구현되어있음
        wd.insert("memo",null,values)//테이블명,작성한 값
        wd.close()
    }

    @SuppressLint("Range") //오류 무시, 내가 완벽하게 짯다고 선언해 주는거
    //현재 메서드인 getColumnIndex 의 Range 가 -1 부터 시작하는데(Autoincrement할 때 index를 0부터 시작하기 위해) getLong은 0이상의 값을 받을 수 있기 때문에 Lint 에서 오류를 발생시키는 것입니다.
    fun selectMemo(): MutableList<Memo>{//전체 데이터 조회
        val list = mutableListOf<Memo>()
        val select = "select * from memo" //조회 쿼리 작성
        val rd = readableDatabase
        val cursor = rd.rawQuery(select,null)//커서 : 데이터셋을 처리할 때 현재 위치를 포함하는 데이터요소
        while (cursor.moveToNext()){
            val no = cursor.getLong(cursor.getColumnIndex("no"))
            val content = cursor.getString(cursor.getColumnIndex("content"))
            val datetime = cursor.getLong(cursor.getColumnIndex("datetime"))
            list.add(Memo(no,content,datetime))
        }
        cursor.close()
        rd.close()
        return list
    }

    fun updateMemo(memo : Memo){
        val values = ContentValues()
        values.put("content",memo.content)
        values.put("datetime",memo.datetime)
        val wd = writableDatabase
        wd.update("memo",values,"no = ${memo.no}",null)
        wd.close()
    }

    fun deleteMemo(memo : Memo){
        val delete = "delete from memo where no = ${memo.no}"
        val wb = writableDatabase
        wb.execSQL(delete)
        wb.close()
    }
}