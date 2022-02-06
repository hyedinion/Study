package com.example.intership_scrapproject_android.presentation.post.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.intership_scrapproject_android.core.util.OrderType
import com.example.intership_scrapproject_android.ui.theme.IntershipScrapProjectAndroidTheme


@Composable
fun PostAppBar(
    isOrderSectionVisible : Boolean,
    onOrderSectionButtonPress : () -> Unit,
    onSearchButtonPress : () -> Unit,
    orderType: OrderType
){
    Box(
        modifier = Modifier
            .height(58.dp)
            .fillMaxWidth()
    ) {

        lateinit var orderText : String
        when(orderType){
            OrderType.SCRAP_DATE -> orderText = "최근 스크랩순"
            OrderType.POST_DATE -> orderText = "최근 등록순"
            OrderType.KEYWORD -> orderText = "키워드별"
        }

        Row(
            modifier = Modifier
                .align(Alignment.CenterStart)
        ) {
            Text(
                modifier = Modifier
                    .padding(15.dp),
                text = orderText,
                style = MaterialTheme.typography.h5
            )
            IconButton(
                modifier = Modifier
                    .padding(10.dp)
                    .alpha(ContentAlpha.medium),
                onClick = { onOrderSectionButtonPress() }
            ) {
                if (isOrderSectionVisible) {
                    Icon(
                        modifier = Modifier
                            .size(30.dp),
                        imageVector = Icons.Filled.ArrowDropDown,
                        contentDescription = "postOrderSectionDropDownIcon",
                    )
                }
                else{
                    Icon(
                        modifier = Modifier
                            .size(30.dp),
                        imageVector = Icons.Filled.ArrowDropUp,
                        contentDescription = "postOrderSectionDropUpIcon",
                    )
                }
            }
        }

        IconButton(
            modifier = Modifier
                .padding(10.dp)
                .align(Alignment.CenterEnd),
            onClick = {onSearchButtonPress()}
        ) {
            Icon(
                modifier = Modifier
                    .alpha(ContentAlpha.medium)
                    .size(30.dp),
                imageVector = Icons.Filled.Search,
                contentDescription = "postSearchIcon",
            )
        }

    }
}


@Preview(showBackground = true)
@Composable
fun PostAppBarPreview() {
    IntershipScrapProjectAndroidTheme {
        PostAppBar(
            isOrderSectionVisible = false,
            onOrderSectionButtonPress = {},
            onSearchButtonPress = {},
            orderType = OrderType.SCRAP_DATE
        )
    }
}