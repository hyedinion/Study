package com.example.mvvm_di_example.data.db

import androidx.lifecycle.LiveData
import com.example.mvvm_di_example.data.model.Quote

interface QuoteDao {
    fun addQuote(quote : Quote)
    //QuoteDao에서 liveData로 넘겨줘야 해당 QuoteList가 update되면 알아서 반영할 수 있음
    //QuoteDao에서 QuoteList를 수정할거기 때문에 여기서 LiveData로 선언해 줘야함.
    //딴곳에서는 QuoteList를 수정할 수 없고 Observe만 할 수 있음
    fun getQuotes() : LiveData<List<Quote>>

}