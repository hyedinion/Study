package com.example.mvvm_di.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.example.mvvm_di.databinding.ActivityQuoteBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class QuoteActivity : AppCompatActivity() {
    val binding by lazy{ActivityQuoteBinding.inflate(layoutInflater)}
    private val quoteViewModel: QuoteViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}