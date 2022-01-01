package com.example.imgdownload_example

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.imgdownload_example.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.net.URL

suspend fun loadImage(imageUrl : String) : Bitmap{//bitmap이미지로 저장
    val url = URL(imageUrl)
    val stream = url.openStream() //url에 있는 스트림을 다운받음
    return BitmapFactory.decodeStream(stream)

}

class MainActivity : AppCompatActivity() {
    val binding by lazy {ActivityMainBinding.inflate(layoutInflater)}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

//        binding.btnDownload.setOnClickListener {
//            CoroutineScope(Dispatchers.Main).launch { //컨텍스트는 Main으로 입력해서 UI요소를 다룰 수 있게 해줌
//                binding.progressBar.visibility = View.VISIBLE
//                val url = binding.editUrl.text.toString()
//                val bitmap = withContext(Dispatchers.IO){//백그라운드 처리를 위해 IO 컨텍스트 사용
//                    loadImage(url)
//                }// suspend 함수이기 때문에 비트맵이 생성되기 전까지는 멈춰있음
//                binding.imagePreview.setImageBitmap(bitmap)
//                binding.progressBar.visibility = View.GONE
//            }
//        }

        binding.run {//binding.run 스코프로 감싸면 binding.을 제거할 수 있음
            btnDownload.setOnClickListener {
                CoroutineScope(Dispatchers.Main).launch { //컨텍스트는 Main으로 입력해서 UI요소를 다룰 수 있게 해줌
                    progressBar.visibility = View.VISIBLE
                    val url = editUrl.text.toString()
                    val bitmap = withContext(Dispatchers.IO){//백그라운드 처리를 위해 IO 컨텍스트 사용
                        loadImage(url)
                    }// suspend 함수이기 때문에 비트맵이 생성되기 전까지는 멈춰있음
                    imagePreview.setImageBitmap(bitmap)
                    progressBar.visibility = View.GONE
                }
            }
        }

    }
}