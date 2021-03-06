package com.example.intership_scrapproject_android.presentation.blog_search.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.TurnedIn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.intership_scrapproject_android.data.remote.response.BlogSearchItem
import com.example.intership_scrapproject_android.ui.theme.IntershipScrapProjectAndroidTheme
import com.example.intership_scrapproject_android.util.TestTags

@Composable
fun BlogSearchItemBox(
    item : BlogSearchItem,
    modifier : Modifier = Modifier,
    scrapped : Boolean = false
){
    Box (
        modifier = modifier
            .fillMaxWidth()
    ){
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(15.dp)
        ) {
            Text(
                text = item.bloggername,
                style = MaterialTheme.typography.h6,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text = item.postdate,
                style = MaterialTheme.typography.body2,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                modifier = Modifier.testTag(TestTags.blogSearchItemTitle),
                text = item.title,
                style = MaterialTheme.typography.h5,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = item.description,
                style = MaterialTheme.typography.body1,
                maxLines = 3,
                overflow = TextOverflow.Ellipsis
            )
        }

        if (scrapped){
            Box(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(10.dp)
                    .testTag(TestTags.blogSearchItemLinkIcon)
            ) {
                Icon(
                    imageVector = Icons.Default.TurnedIn,
                    contentDescription = "scrapped icon",
                    tint = MaterialTheme.colors.primary
                )
            }
        }

    }
    Divider(color = MaterialTheme.colors.onSurface, thickness = 1.dp)

}

@Preview(showBackground = true)
@Composable
fun BlogSearchItemPreview() {

    val item = BlogSearchItem(
        bloggerlink = "",
        bloggername = "??????????????? ??????",
        title = "Happy New Year & Thanks",
        description = "(???????????? ??? ?????????.) Happy New Year, ?????? ?????? ?????? ???????????? ?????? ?????? ?????? ??? ?????? ??? ????????????. ?????? ????????? ??? ??? ?????? ????????? ?????? ??? ?????????????????? ?????? ????????? ???????????????... ?????? ?????? ???????????? 2021??????... ",
        link = "https://blog.naver.com/sub_cat?Redirect=Log&logNo=222609846359",
        postdate = "20211231",
        )
    IntershipScrapProjectAndroidTheme {
        LazyColumn {
            item {  BlogSearchItemBox(item, scrapped = true) }
        }

    }
}