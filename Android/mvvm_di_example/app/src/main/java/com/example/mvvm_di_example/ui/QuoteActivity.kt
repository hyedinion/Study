package com.example.mvvm_di_example.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.example.mvvm_di_example.R
import com.example.mvvm_di_example.data.model.Quote
import com.example.mvvm_di_example.databinding.ActivityQuoteBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class QuoteActivity : AppCompatActivity() {
    private val quoteViewModel : QuoteViewModel by viewModels()
    val binding by lazy { ActivityQuoteBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        quoteViewModel.getQuote().observe(this, Observer { quotes ->
            val stringBuilder = StringBuilder()
            quotes.forEach { quote ->
                stringBuilder.append("$quote\n\n")
            }
            binding.textView.text = stringBuilder.toString()

        })

        binding.btnAddQuote.setOnClickListener {
            val quote = Quote(binding.textQuote.text.toString(), binding.textAuthor.text.toString())
            quoteViewModel.addQuote(quote)
            binding.textQuote.setText("")
            binding.textAuthor.setText("")
        }
    }
}