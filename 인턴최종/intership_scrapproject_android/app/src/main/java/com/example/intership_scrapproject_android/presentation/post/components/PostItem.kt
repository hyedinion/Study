package com.example.intership_scrapproject_android.presentation.post.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.intership_scrapproject_android.util.OrderType
import com.example.intership_scrapproject_android.data.local.Post
import com.example.intership_scrapproject_android.ui.theme.IntershipScrapProjectAndroidTheme
import com.example.intership_scrapproject_android.util.TestTags

@Composable
fun PostItem(
    modifier: Modifier,
    orderType: OrderType,
    post : Post,
){
    Box (
        modifier = modifier
            .fillMaxWidth()
            .testTag(TestTags.postItem)
    ){
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(15.dp)
        ) {
            Text(
                text = post.bloggerName,
                style = MaterialTheme.typography.h6,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            var postDateColor = Color.Unspecified
            var scrapDateColor = Color.Unspecified
            when(orderType){
                OrderType.POST_DATE -> postDateColor = MaterialTheme.colors.primary
                OrderType.SCRAP_DATE -> scrapDateColor = MaterialTheme.colors.primary
            }

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = post.postDate,
                    style = MaterialTheme.typography.body2,
                    color = postDateColor
                )
                Text(text = " | ")
                Text(
                    text = post.scrapDate.split(":")[0],
                    style = MaterialTheme.typography.body2,
                    color = scrapDateColor
                )
            }

            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = post.title,
                style = MaterialTheme.typography.h5,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = post.description,
                style = MaterialTheme.typography.body1,
                maxLines = 3,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
    Divider(color = MaterialTheme.colors.onSurface, thickness = 1.dp)

}

@Preview(showBackground = true)
@Composable
fun PostItemPreview() {

    val post =  Post(
        title = "Happy New Year & Thanks",
        description = "(???????????? ??? ?????????.) Happy New Year, ?????? ?????? ?????? ???????????? ?????? ?????? ?????? ??? ?????? ??? ????????????. ?????? ????????? ??? ??? ?????? ????????? ?????? ??? ?????????????????? ?????? ????????? ???????????????... ?????? ?????? ???????????? 2021??????... ",
        link = "https://blog.naver.com/sub_cat?Redirect=Log&logNo=222609846359",
        keyword = "search",
        scrapDate = "20220101:hhdd",
        postDate = "20211231",
        bloggerName = "??????????????? ??????"
    )
    IntershipScrapProjectAndroidTheme {
        LazyColumn {
            item {
                PostItem(
                    modifier = Modifier,
                    post = post,
                    orderType = OrderType.POST_DATE
                )
            }
        }
    }

}