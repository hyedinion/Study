package com.example.file_example

import java.io.*

class FileUtil {
    fun readTextFile(fullPath : String) : String{
        val file = File(fullPath)
        if (!file.exists()) return ""//실제 파일이 있는지 검사

        val reader = FileReader(file)
        val buffer = BufferedReader(reader)//버퍼에 담으면 속도가 향상됨
        var temp = "" //버퍼를 통해 한줄씩 읽은 내용을 임시로 저장할 변수
        val result = StringBuffer()//모든 내용을 저장할 버퍼
        while(true){
            temp = buffer.readLine()
            if (temp==null)break
            else result.append(buffer)
        }
        buffer.close()
        return result.toString()
    }
    fun writeTextFile(directory: String, filename : String, content : String){
        val dir = File(directory)
        if (!dir.exists()) dir.mkdirs()
        val writer = FileWriter(directory + "/"+filename)
        val buffer = BufferedWriter(writer)
        buffer.write(content)
        buffer.close()
    }
}