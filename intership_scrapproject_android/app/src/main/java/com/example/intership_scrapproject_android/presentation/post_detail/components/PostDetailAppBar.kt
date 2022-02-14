package com.example.intership_scrapproject_android.presentation.post_detail.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.intership_scrapproject_android.ui.theme.IntershipScrapProjectAndroidTheme


@Composable
fun PostDetailAppBar(
    onBackPress : () -> Unit,
    onDeleteButtonPress : () -> Unit,
    onLinkButtonPress : () -> Unit,
    onEditButtonPress : () -> Unit,
){
    Box(
        modifier = Modifier
            .height(58.dp)
            .fillMaxWidth()
    ) {

        IconButton(
            modifier = Modifier
                .padding(10.dp)
                .alpha(ContentAlpha.medium)
                .align(Alignment.CenterStart),
            onClick = {onBackPress()}
        ) {
            Icon(
                modifier = Modifier
                    .alpha(ContentAlpha.medium)
                    .size(30.dp),
                imageVector = Icons.Filled.ArrowBackIos,
                contentDescription = "Back",
            )
        }

        Row(
            modifier = Modifier
                .align(Alignment.CenterEnd),
        ) {

            IconButton(
                modifier = Modifier
                    .alpha(ContentAlpha.medium),
                onClick = { onDeleteButtonPress() }
            ) {
                Icon(
                    modifier = Modifier
                        .size(30.dp),
                    imageVector = Icons.Filled.Delete,
                    contentDescription = "Scrap",
                )
            }

            IconButton(
                modifier = Modifier
                    .alpha(ContentAlpha.medium),
                onClick = { onLinkButtonPress() }
            ) {
                Icon(
                    modifier = Modifier
                        .size(30.dp),
                    imageVector = Icons.Default.Link,
                    contentDescription = "Scrap",
                )
            }

            IconButton(
                modifier = Modifier
                    .alpha(ContentAlpha.medium),
                onClick = { onEditButtonPress() }
            ) {
                Icon(
                    modifier = Modifier
                        .size(30.dp),
                    imageVector = Icons.Default.Edit,
                    contentDescription = "Scrap",
                )
            }
        }



        Divider(
            modifier = Modifier.align(Alignment.BottomCenter),
            color = Color.LightGray,
            thickness = 1.dp,
        )
    }
}


@ExperimentalComposeUiApi
@Preview(showBackground = true)
@Composable
fun AppBarPreview() {
    IntershipScrapProjectAndroidTheme {
        PostDetailAppBar(
            onBackPress = {},
            onDeleteButtonPress = {},
            onLinkButtonPress = {},
            onEditButtonPress = {}
        )
    }
}