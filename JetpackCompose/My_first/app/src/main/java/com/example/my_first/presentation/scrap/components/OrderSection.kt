package com.example.my_first.presentation.scrap.components

import android.widget.Space
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.my_first.domain.util.OrderType
import com.example.my_first.domain.util.ScrapOrder
import com.example.my_first.presentation.scrap.DefaultRadioButton

@Composable
fun OrderSection(
    modifier : Modifier = Modifier,
    scrapOrder : ScrapOrder = ScrapOrder.ScrapDate(OrderType.Descending),
    onOrderChange : (ScrapOrder) -> Unit
){
    Column(modifier = modifier) {
        Row(modifier = modifier.fillMaxWidth()) {
            DefaultRadioButton(
                text = "Keyword",
                selected = scrapOrder is ScrapOrder.Keyword,//scrapOrder가 keyword이면 true, selected된거임
                onSelect = { onOrderChange(ScrapOrder.Keyword(scrapOrder.orderType)) }
            )
            Spacer(modifier = Modifier.width(8.dp))
            DefaultRadioButton(
                text = "ScrapDate",
                selected = scrapOrder is ScrapOrder.ScrapDate,//scrapOrder가 Scrapdate이면 true, selected된거임
                onSelect = { onOrderChange(ScrapOrder.ScrapDate(scrapOrder.orderType)) }
            )
            Spacer(modifier = Modifier.width(8.dp))
            DefaultRadioButton(
                text = "PostDate",
                selected = scrapOrder is ScrapOrder.PostDate,//scrapOrder가 PostDate이면 true, selected된거임
                onSelect = { onOrderChange(ScrapOrder.PostDate(scrapOrder.orderType)) }
            )

        }
        Spacer(modifier = Modifier.height(16.dp))
        Row(modifier = modifier.fillMaxWidth()) {
            DefaultRadioButton(
                text = "Ascending",
                selected = scrapOrder.orderType is OrderType.Ascending,//scrapOrder가 keyword이면 true, selected된거임
                onSelect = {
                    onOrderChange(scrapOrder.copy(OrderType.Ascending))
                }
            )
            Spacer(modifier = Modifier.width(8.dp))
            DefaultRadioButton(
                text = "Descending",
                selected = scrapOrder.orderType is OrderType.Descending,//scrapOrder가 Scrapdate이면 true, selected된거임
                onSelect = { onOrderChange(scrapOrder.copy(OrderType.Descending)) }
            )
        }

    }
}