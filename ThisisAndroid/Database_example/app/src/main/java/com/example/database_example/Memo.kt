package com.example.database_example

data class Memo(var no : Long?, var content : String, var datetime : Long)
// no : nullable인 이유 -> AutoIncrement 이니까 데이터 삽입시에는 no값이 필요없음
// sqlite에서 integer로 선언한 것은 그냥 Long으로 쓰삼, 숫자의 범위가 다 다르기 때문
