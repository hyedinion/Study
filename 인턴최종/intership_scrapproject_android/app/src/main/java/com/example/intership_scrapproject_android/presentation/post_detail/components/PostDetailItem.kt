package com.example.intership_scrapproject_android.presentation.post_detail.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.intership_scrapproject_android.data.local.Post
import com.example.intership_scrapproject_android.ui.theme.IntershipScrapProjectAndroidTheme
import com.example.intership_scrapproject_android.util.TestTags

@Composable
fun PostDetailItem(
    item : Post,
    keyword : String,
    modifier : Modifier = Modifier
){
    Box (
        modifier = modifier
            .fillMaxSize()
            .testTag(TestTags.postDetailItem)
    ){
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(15.dp)
        ) {
            Row {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "blogSearchDetailIcon",
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
            Spacer(modifier = Modifier.height(8.dp))


            Text(
                text = item.title,
                style = MaterialTheme.typography.h5,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.height(12.dp))


            Text(
                text = item.bloggerName,
                style = MaterialTheme.typography.h6,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text = item.postDate,
                style = MaterialTheme.typography.body2,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.height(20.dp))
            Divider(
                modifier = Modifier.alpha(ContentAlpha.medium),
                color = Color.LightGray,
                thickness = 1.dp
            )

            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = item.description,
                style = MaterialTheme.typography.body1
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BlogDetailScreenPreview() {
    val item = Post(
        title = "Happy New Year & Thanks",
        description = "(???????????? ??? ?????????.) Happy New Year, ?????? ?????? ?????? ???????????? ?????? ?????? ?????? ??? ?????? ??? ????????????. ?????? ????????? ??? ??? ?????? ????????? ?????? ??? ?????????????????? ?????? ????????? ???????????????... ?????? ?????? ???????????? 2021??????... ",
        link = "https://blog.naver.com/sub_cat?Redirect=Log&logNo=222609846359",
        keyword = "happy",
        scrapDate = "20211231",
        postDate = "20211231",
        bloggerName = "??????????????? ??????",
        id = null
    )
    IntershipScrapProjectAndroidTheme {
        PostDetailItem(item, "search")
    }

}