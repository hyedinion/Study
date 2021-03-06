package com.example.intership_scrapproject_android.presentation.post_edit.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.intership_scrapproject_android.data.local.Post
import com.example.intership_scrapproject_android.ui.theme.IntershipScrapProjectAndroidTheme
import com.example.intership_scrapproject_android.util.TestTags

@Composable
fun PostEditItem(
    item : Post,
    title : String,
    onTitleTextChange : (String) -> Unit,
    description : String,
    onDescriptionTextChange : (String) -> Unit,
    modifier : Modifier = Modifier
){
    Box (
        modifier = modifier
            .fillMaxSize()
            .testTag(TestTags.postEditItem)
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
                    text = item.keyword,
                    style = MaterialTheme.typography.body1,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    color = MaterialTheme.colors.primary
                )
            }
            Spacer(modifier = Modifier.height(8.dp))

            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag(TestTags.postEditTitleTextField)
                ,
                value = title,
                onValueChange = { onTitleTextChange(it) },
                textStyle = TextStyle(
                    color = Color.LightGray,
                    fontWeight = MaterialTheme.typography.h5.fontWeight,
                    fontSize = MaterialTheme.typography.h5.fontSize,
                    letterSpacing = MaterialTheme.typography.h5.letterSpacing
                ),
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color.Transparent,
                    cursorColor = Color.Gray.copy(alpha = ContentAlpha.medium)
                )
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
            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag(TestTags.postEditDescriptionTextField)
                ,
                value = description,
                onValueChange = { onDescriptionTextChange(it) },
                textStyle = TextStyle(
                    color = Color.LightGray,
                    fontWeight = MaterialTheme.typography.body1.fontWeight,
                    fontSize = MaterialTheme.typography.body1.fontSize,
                    letterSpacing = MaterialTheme.typography.body1.letterSpacing
                ),
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color.Transparent,
                    cursorColor = Color.Gray.copy(alpha = ContentAlpha.medium)
                )
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
        PostEditItem( item, item.title, {}, item.description, {}
        )
    }

}