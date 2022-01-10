package com.example.mvvm_di_example.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.mvvm_di_example.data.QuoteRepository
import com.example.mvvm_di_example.data.model.Quote
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class QuoteViewModel @Inject constructor(private val quoteRepository: QuoteRepository) : ViewModel() {
    fun addQuote(quote : Quote){
        quoteRepository.addQuote(quote)
    }
    fun getQuote() = quoteRepository.getQuote()
}