package com.example.my_first.presentation.Search

import com.example.my_first.domain.model.Scrap
import com.example.my_first.domain.util.ScrapOrder

sealed class SearchEvent{//scrap ui에서 일어날 수 있는 event 모음
    object searchOrder : SearchEvent() //Search Order이 바뀌면 event 전송

}
