package com.example.mvvm_di_example.data

import androidx.lifecycle.LiveData
import com.example.mvvm_di_example.data.model.Quote

interface QuoteRepository {
    fun addQuote(quote : Quote)
    fun getQuote() : LiveData<List<Quote>>
}