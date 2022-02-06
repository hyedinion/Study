package com.example.intership_scrapproject_android.presentation.post.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.room.FtsOptions
import com.example.intership_scrapproject_android.core.util.OrderType
import com.example.intership_scrapproject_android.ui.theme.IntershipScrapProjectAndroidTheme

@Composable
fun PostOrderSection(
    orderType: OrderType = OrderType.KEYWORD,
    onOrderChange : (OrderType) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        TextButton(onClick = { onOrderChange(OrderType.SCRAP_DATE) }) {
            if(orderType==OrderType.SCRAP_DATE) {
                Text(
                    text = "최근 스크랩순",
                    color = MaterialTheme.colors.primary
                )
            }else{
                Text(
                    text = "최근 스크랩순",
                )
            }
        }
        TextButton(onClick = { onOrderChange(OrderType.POST_DATE) }) {
            if(orderType==OrderType.POST_DATE) {
                Text(
                    text = "최근 등록순",
                    color = MaterialTheme.colors.primary
                )
            }else{
                Text(
                    text = "최근 등록순",
                )
            }
        }
        TextButton(onClick = { onOrderChange(OrderType.KEYWORD) }) {
            if(orderType==OrderType.KEYWORD) {
                Text(
                    text = "키워드별",
                    color = MaterialTheme.colors.primary
                )
            }else{
                Text(
                    text = "키워드별",
                )
            }
        }

    }
}


@Preview(showBackground = true)
@Composable
fun PostOrderSectionPreview() {
    IntershipScrapProjectAndroidTheme {
        PostOrderSection(
            orderType = OrderType.SCRAP_DATE,
            onOrderChange = {}
        )
    }
}