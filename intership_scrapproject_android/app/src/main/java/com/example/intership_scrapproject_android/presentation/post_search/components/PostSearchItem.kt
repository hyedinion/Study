package com.example.intership_scrapproject_android.presentation.post_search.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.TurnedIn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.intership_scrapproject_android.data.local.Post
import com.example.intership_scrapproject_android.ui.theme.IntershipScrapProjectAndroidTheme

@Composable
fun PostSearchItem(
    keyword : String,
    item : Post,
    modifier : Modifier = Modifier
){
    val keywordInTitleIndex = item.title.indexOf(keyword)
    val lastKeywordInTitleIndex = keywordInTitleIndex+(keyword.length)
    val keywordInDescriptionIndex = item.description.indexOf(keyword)
    val lastKeywordInDescriptionIndex = keywordInDescriptionIndex+(keyword.length)

    Box (
        modifier = modifier
            .fillMaxWidth()
            .background(Color.White)
    ){
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(15.dp)
        ) {

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
            Spacer(modifier = Modifier.height(8.dp))

            if (keywordInTitleIndex >= 0) {
                Text(
                    text = buildAnnotatedString {
                        append(item.title.substring(0 until keywordInTitleIndex))
                        withStyle(
                            SpanStyle(
                                color = MaterialTheme.colors.primary
                            )
                        ){
                            append(item.title.substring(keywordInTitleIndex until lastKeywordInTitleIndex))
                        }
                        append(item.title.substring(lastKeywordInTitleIndex))
                    },
                    style = MaterialTheme.typography.h5,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
            } else {
                Text(
                    text = item.title,
                    style = MaterialTheme.typography.h5,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            if (keywordInDescriptionIndex >= 0) {
                Text(
                    text = buildAnnotatedString {
                        append(item.description.substring(0 until keywordInDescriptionIndex))
                        withStyle(
                            SpanStyle(
                                color = MaterialTheme.colors.primary
                            )
                        ){
                            append(item.description.substring(keywordInDescriptionIndex until lastKeywordInDescriptionIndex))
                        }
                        append(item.description.substring(lastKeywordInDescriptionIndex))
                    },
                    style = MaterialTheme.typography.body1,
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis,
                )
            }else{
                Text(
                    text = item.description,
                    style = MaterialTheme.typography.body1,
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis,
                )
            }

        }

    }
    Divider(color = MaterialTheme.colors.onSurface, thickness = 1.dp)

}

@Preview(showBackground = true)
@Composable
fun BlogSearchItemPreview() {

    val item = Post(
        title = "Happy New Year & Thanks",
        description = "(아까워서 못 까겠다.) Happy New Year, 라고 적기 전에 잡소리를 너무 길게 남긴 것 같아 좀 부끄럽다. 보기 싫은데 새 글 떠서 억지로 보게 될 이웃님들께는 그저 죄송할 따름이지만... 그냥 길고 힘들었던 2021년을... ",
        link = "https://blog.naver.com/sub_cat?Redirect=Log&logNo=222609846359",
        keyword = "happy",
        scrapDate = "20211231",
        postDate = "20211231",
        bloggerName = "민폐스러운 일상",
        id = null
    )
    IntershipScrapProjectAndroidTheme {
        LazyColumn {
            item {  PostSearchItem("",item) }
        }
    }
}