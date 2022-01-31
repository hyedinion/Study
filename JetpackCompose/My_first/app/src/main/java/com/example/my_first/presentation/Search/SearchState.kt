package com.example.my_first.presentation.Search

import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import com.example.my_first.data.remote.Item
import com.example.my_first.domain.model.Scrap
import com.example.my_first.domain.util.OrderType
import com.example.my_first.domain.util.ScrapOrder
import kotlinx.coroutines.flow.Flow

data class SearchState( //Scrap ui에서 변경될 수 있는 state 모음
    val isSearchOrderList : Boolean = true, //현재 설정창이 열려있는지 상태
    val searchText : String = "",
    val searchList : Flow<PagingData<Item>>? = null,//scrap 목록 상태
)
