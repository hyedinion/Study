package com.example.activity_example

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.activity_example.databinding.ActivitySubBinding

class SubActivity : AppCompatActivity() {
    val binding by lazy {ActivitySubBinding.inflate(layoutInflater)}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.textView3.text = intent.getStringExtra("from1")
        binding.textView4.text = "${intent.getIntExtra("from2",0)}"

        binding.button2.setOnClickListener {
            var returnIntent = Intent()
            returnIntent.putExtra("returnValue",binding.editTextTextPersonName.text.toString())
            setResult(RESULT_OK,returnIntent)
            finish()
        }
    }
}