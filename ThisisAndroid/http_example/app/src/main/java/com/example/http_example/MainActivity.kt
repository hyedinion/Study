package com.example.http_example

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.http_example.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.StringBuilder
import java.net.HttpURLConnection
import java.net.URL

class MainActivity : AppCompatActivity() {
    val binding by lazy{ActivityMainBinding.inflate(layoutInflater)}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btnRequest.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                try {
                    var urlText = binding.editUrl.text.toString()
                    if (!urlText.startsWith("https")){
                        urlText = "https://${urlText}"
                    }
                    val url = URL(urlText) //URL객체로 변환
//openConnection으로 반환되는 값은 URLConnection이라는 추상클래스, 실제 구현클래스인 httpURLConnection으로 변환해야함
                    val urlConnection = url.openConnection() as HttpURLConnection
                    urlConnection.requestMethod = "GET"
                    if (urlConnection.responseCode == HttpURLConnection.HTTP_OK){//responseCode면 벌써 서버에 갔다 온건가..?
                        //스트림 연결
                        val streamReader = InputStreamReader(urlConnection.inputStream)
                        val buffered = BufferedReader(streamReader)
                        //데이터 읽기
                        val content = StringBuilder()
                        while(true){
                            val line = buffered.readLine()?:break
                            content.append(line)
                        }
                        //스트림,커넥션 해제
                        buffered.close()
                        urlConnection.disconnect()

                        launch(Dispatchers.Main) {
                            binding.textContent.text = content.toString()
                        }
                    }
                }catch (e : Exception){
                    e.printStackTrace()//예외 발생 시 로그를 출력, print문 보다 성능 좋음
                }
            }
        }
    }
}