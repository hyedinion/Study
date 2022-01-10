package com.example.mvvm_di_example.data.db

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mvvm_di_example.data.model.Quote

class QuoteDaoFakeImpl : QuoteDao {

    //실제 Database가 없기 때문에 여기서 quoteList를 선언해줌
    //실제 Database가 있으면 이부분은 없어도 됨. DB에서 가져오면 되니까
    private val quoteList = mutableListOf<Quote>()
    //LiveData가 외부에서 변경되는 것을 방지하기 위해서 QuoteDao에서만 update를 해준다
    //그래서 지역변수로 MutableLiveData를 선언했고 return할 때는 LiveData로 변경하지 못하게 한다.
    private val quotes = MutableLiveData<List<Quote>>()

    init {
        quotes.value = quoteList
    }

    //여기서 addQuote를 하면 LiveData가 변경되기 때문에 Observer에게 알림이감
    override fun addQuote(quote: Quote) {
        quoteList.add(quote)
        quotes.value = quoteList
    }

    //quotes를 LiveData로 typeCasting후 반환
    override fun getQuotes() = quotes as LiveData<List<Quote>>

}