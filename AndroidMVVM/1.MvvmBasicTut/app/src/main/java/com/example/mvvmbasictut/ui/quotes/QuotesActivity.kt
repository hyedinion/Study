package com.example.mvvmbasictut.ui.quotes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.mvvmbasictut.R
import com.example.mvvmbasictut.data.Quote
import com.example.mvvmbasictut.databinding.ActivityQuotesBinding
import com.example.mvvmbasictut.utilities.InjectorUtils
import java.lang.StringBuilder

class QuotesActivity : AppCompatActivity() {

    val binding by lazy {ActivityQuotesBinding.inflate(layoutInflater)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initializeUi()
    }

    private fun initializeUi(){
        val factory = InjectorUtils.provideQuotesViewModelFactory()
        val viewModel = ViewModelProviders.of(this,factory) //내가 쓸 viewModel은 factory에서 정해준걸로 하겠다 이런뜻
            .get(QuotesViewModel::class.java)

        //이제 viewModel에 있는 data를 지켜보고 있음
        viewModel.getQuotes().observe(this, Observer { quotes ->
            val stringBuilder = StringBuilder()
            quotes.forEach { quote ->
                stringBuilder.append("$quote\n\n")
            }
            binding.textViewQuotes.text = stringBuilder.toString()
        })

        binding.buttonAddQuote.setOnClickListener {
            val quote = Quote(binding.editTextQuote.text.toString(), binding.editTextAuthor.text.toString())
            viewModel.addQuotes(quote)
            binding.editTextQuote.setText("")
            binding.editTextAuthor.setText("")
        }

    }
}