package com.example.my_first.presentation.scrap

import com.example.my_first.domain.model.Scrap
import com.example.my_first.domain.util.OrderType
import com.example.my_first.domain.util.ScrapOrder

data class ScrapState( //Scrap ui에서 변경될 수 있는 state 모음
    val scraps : List<Scrap> = emptyList(),//scrap 목록 상태
    val scrapOrder: ScrapOrder = ScrapOrder.ScrapDate(OrderType.Descending),//라디오버튼 order 상태
    val isOrderSectionVisible : Boolean = false //현재 설정창이 열려있는지 상태
)
