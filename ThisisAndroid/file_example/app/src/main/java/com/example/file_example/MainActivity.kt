package com.example.file_example

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import java.io.File

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val contents = "Hello\nWorld"
        openFileOutput("test.txt",MODE_PRIVATE).use{ stream ->
            stream.write(contents.toByteArray())
        }

        val file = File(baseContext.filesDir,"test.txt")
        Log.d("textfile","${file.absolutePath}")

        var contents2 = ""
        openFileInput("test.txt").bufferedReader().useLines{ lines ->
            contents2 = lines.joinToString("\n")
        }
        Log.d("textfile","${contents2}")
    }
}