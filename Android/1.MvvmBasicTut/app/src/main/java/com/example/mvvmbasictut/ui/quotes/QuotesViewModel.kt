package com.example.mvvmbasictut.ui.quotes

import androidx.lifecycle.ViewModel
import com.example.mvvmbasictut.data.Quote
import com.example.mvvmbasictut.data.QuoteRepository

class QuotesViewModel(private val quoteRepository: QuoteRepository) : ViewModel() {

    fun getQuotes() = quoteRepository.getQuote()
    fun addQuotes(quote: Quote) = quoteRepository.addQuote(quote)
}