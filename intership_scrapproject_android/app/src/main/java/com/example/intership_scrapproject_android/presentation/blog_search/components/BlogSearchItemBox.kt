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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.intership_scrapproject_android.data.remote.response.BlogSearchItem
import com.example.intership_scrapproject_android.ui.theme.IntershipScrapProjectAndroidTheme

@Composable
fun BlogSearchItemBox(
    item : BlogSearchItem,
    modifier : Modifier = Modifier,
    scrapped : Boolean = false
){
    var postDate = ""
    if (item.postdate.isNotEmpty()) {
        postDate =
            item.postdate.substring(0..3) + "." + item.postdate.substring(4..5) + "." + item.postdate.substring(
                6..7
            )
    }
    Box (
        modifier = modifier
            .fillMaxWidth()
    ){
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
                .padding(end = 32.dp)
        ) {
            Text(
                text = item.bloggername,
                style = MaterialTheme.typography.body2,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text = postDate,
                style = MaterialTheme.typography.body2,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = item.title,
                style = MaterialTheme.typography.h6,
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
                modifier = modifier
                    .align(Alignment.TopEnd)
                    .padding(10.dp)
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
        bloggername = "민폐스러운 일상",
        title = "Happy New Year & Thanks",
        description = "(아까워서 못 까겠다.) Happy New Year, 라고 적기 전에 잡소리를 너무 길게 남긴 것 같아 좀 부끄럽다. 보기 싫은데 새 글 떠서 억지로 보게 될 이웃님들께는 그저 죄송할 따름이지만... 그냥 길고 힘들었던 2021년을... ",
        link = "https://blog.naver.com/sub_cat?Redirect=Log&logNo=222609846359",
        postdate = "20211231",
        )
    IntershipScrapProjectAndroidTheme {
        LazyColumn {
            item {  BlogSearchItemBox(item, scrapped = true) }
        }

    }
}