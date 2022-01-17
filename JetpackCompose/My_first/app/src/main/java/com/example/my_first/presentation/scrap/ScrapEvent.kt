package com.example.my_first.presentation.scrap

import com.example.my_first.domain.model.Scrap
import com.example.my_first.domain.util.ScrapOrder

sealed class ScrapEvent{//scrap ui에서 일어날 수 있는 event 모음
    data class Order(val scrapOrder: ScrapOrder): ScrapEvent() // order가 변경되면 viewmodel에게 event 전송
    data class DeleteScrap(val scrap : Scrap): ScrapEvent() //scrap 삭제 event 전송
    object ToggleOrderSection : ScrapEvent() //order section 버튼이 눌리면 event 전송

}
