package com.example.intership_scrapproject_android.presentation.blog_search_detail.components

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
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.intership_scrapproject_android.ui.theme.IntershipScrapProjectAndroidTheme
import com.example.intership_scrapproject_android.util.TestTags


@Composable
fun BlogDetailAppBar(
    onBackPress : () -> Unit,
    onScrapButtonPress : () -> Unit,
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
                .align(Alignment.CenterStart)
            ,
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

        IconButton(
            modifier = Modifier
                .padding(10.dp)
                .alpha(ContentAlpha.medium)
                .align(Alignment.CenterEnd)
                .testTag(TestTags.blogScrapButton),
            onClick = {onScrapButtonPress()}
        ) {
            Icon(
                modifier = Modifier
                    .size(30.dp),
                imageVector = Icons.Filled.TurnedInNot,
                contentDescription = "Scrap",
            )
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
        BlogDetailAppBar(
            onBackPress = {},
            onScrapButtonPress = {}
        )
    }
}