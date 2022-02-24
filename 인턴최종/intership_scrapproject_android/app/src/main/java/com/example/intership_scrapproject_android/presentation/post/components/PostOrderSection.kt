package com.example.intership_scrapproject_android.presentation.post.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import com.example.intership_scrapproject_android.util.OrderType
import com.example.intership_scrapproject_android.ui.theme.IntershipScrapProjectAndroidTheme
import com.example.intership_scrapproject_android.util.TestTags

@Composable
fun PostOrderSection(
    orderType: OrderType,
    onOrderChange : (OrderType) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .testTag(TestTags.postOrderSection)
        ,
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

        TextButton(
            modifier = Modifier.testTag(TestTags.postScrapDateTextButton),
            onClick = { onOrderChange(OrderType.SCRAP_DATE) }
        ) {
            Text(
                text = "최근 스크랩순",
                color = scrapDateColor
            )
        }
        TextButton(
            modifier = Modifier.testTag(TestTags.postPostDateTextButton),
            onClick = { onOrderChange(OrderType.POST_DATE) }
        ) {
            Text(
                text = "최근 등록순",
                color = postDateColor
            )
        }
        TextButton(
            modifier = Modifier.testTag(TestTags.postKeywordTextButton),
            onClick = { onOrderChange(OrderType.KEYWORD) }
        ) {
            Text(
                text = "키워드별",
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