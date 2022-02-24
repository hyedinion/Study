package com.example.intership_scrapproject_android.presentation.post.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.intership_scrapproject_android.ui.theme.IntershipScrapProjectAndroidTheme
import com.example.intership_scrapproject_android.util.TestTags

@Composable
fun PostKeywordHeader(
    keyword : String
){
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colors.onSurface)
            .testTag(TestTags.postItemHeader)
    ) {

        Row(
            modifier = Modifier
                .padding(10.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "postKeywordHeaderIcon",
                tint = MaterialTheme.colors.primary
            )
            Text(
                text = keyword,
                style = MaterialTheme.typography.body1,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                color = MaterialTheme.colors.primary
            )
        }

        Divider(
            modifier = Modifier.align(Alignment.BottomCenter),
            color = Color.LightGray,
            thickness = 1.dp,
        )

    }
}


@Preview(showBackground = true)
@Composable
fun PostKeywordHeaderPreview() {
    IntershipScrapProjectAndroidTheme {
        PostKeywordHeader("happy")
    }
}