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
    orderType: OrderType,
    onOrderChange : (OrderType) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceAround
    ) {

        var scrapDateColor = Color.Unspecified
        var postDateColor = Color.Unspecified
        var keywordColor = Color.Unspecified

        when(orderType){
            OrderType.SCRAP_DATE -> scrapDateColor = MaterialTheme.colors.primary
            OrderType.POST_DATE -> postDateColor = MaterialTheme.colors.primary
            OrderType.KEYWORD -> keywordColor = MaterialTheme.colors.primary
        }

        TextButton(onClick = { onOrderChange(OrderType.SCRAP_DATE) }) {
            Text(
                text = "최근 스크랩순",
                color = scrapDateColor
            )
        }
        TextButton(onClick = { onOrderChange(OrderType.POST_DATE) }) {
            Text(
                text = "최근 등록순",
                color = postDateColor
            )
        }
        TextButton(onClick = { onOrderChange(OrderType.KEYWORD) }) {
            Text(
                text = "키워드순",
                color = keywordColor
            )
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