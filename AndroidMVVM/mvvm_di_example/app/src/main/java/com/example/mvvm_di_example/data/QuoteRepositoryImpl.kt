package com.example.mvvm_di_example.data

import androidx.lifecycle.LiveData
import com.example.mvvm_di_example.data.db.QuoteDao
import com.example.mvvm_di_example.data.model.Quote
import javax.inject.Inject

//생성자 주입을 통해 applicationComponent에 바인딩 할거임
class QuoteRepositoryImpl (private val quoteDao : QuoteDao) : QuoteRepository {
    override fun addQuote(quote: Quote) {
        quoteDao.addQuote(quote)
    }

    override fun getQuote() = quoteDao.getQuotes()
}