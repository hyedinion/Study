package com.example.layout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.SeekBar
import androidx.core.widget.addTextChangedListener
import com.example.layout.databinding.ActivityMainBinding
import com.example.layout.databinding.ProgressBinding
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {
    val binding by lazy { ProgressBinding.inflate(layoutInflater)}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        //Thread.sleep(3000)
        //showProgress(false)
        thread(start = true){
            Thread.sleep(3000)
            runOnUiThread {
                showProgress(false)
            }
        }

        binding.seekBar3.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                binding.textView7.text = "$p1"
                //p1 : progress
                //p2 : fromUser 사용자의 터치 여부 (코드에서 움직인게 아니라 사용자가 움직인건지 확인용)
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {
                //TODO("Not yet implemented")
            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
                //TODO("Not yet implemented")
            }
        })

        binding.ratingBar.setOnRatingBarChangeListener { ratingBar, fl, b ->
            //fl : rating : 현재 별점
            //b : fromUser : 사용자 입력여부
            binding.textView7.text = "$fl"
        }


    }
    fun showProgress(show : Boolean){
        binding.progressBar2.visibility = if(show) View.VISIBLE else View.GONE
    }
}