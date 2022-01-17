package com.example.my_first.domain.use_case

import com.example.my_first.domain.model.Scrap
import com.example.my_first.domain.repository.ScrapRepository
import com.example.my_first.domain.util.OrderType
import com.example.my_first.domain.util.ScrapOrder
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetScraps(
    private val repository: ScrapRepository
) {

    operator fun invoke(
        scrapOrder: ScrapOrder = ScrapOrder.ScrapDate(OrderType.Descending)//default 정렬
    ) : Flow<List<Scrap>>{
        return repository.getScrap().map { scraps->
            when(scrapOrder.orderType){
                is OrderType.Ascending->{
                    when(scrapOrder){
                        is ScrapOrder.Keyword -> scraps.sortedBy { it.keyword.lowercase() }
                        is ScrapOrder.ScrapDate -> scraps.sortedBy { it.scrapDate }
                        is ScrapOrder.PostDate -> scraps.sortedBy { it.postDate }
                    }
                }
                is OrderType.Descending ->{
                    when(scrapOrder){
                        is ScrapOrder.Keyword -> scraps.sortedByDescending { it.keyword.lowercase() }
                        is ScrapOrder.ScrapDate -> scraps.sortedByDescending { it.scrapDate }
                        is ScrapOrder.PostDate -> scraps.sortedByDescending { it.postDate }
                    }
                }
            }
        }
    }
}