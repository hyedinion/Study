package com.example.intership_scrapproject_android.presentation.blog_search_scrap.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.TurnedIn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import com.example.intership_scrapproject_android.data.remote.response.BlogSearchItem
import com.example.intership_scrapproject_android.ui.theme.IntershipScrapProjectAndroidTheme

@Composable
fun BlogScrapItem(
    item : BlogSearchItem,
    keyword : String,
    title : String,
    onTitleTextChange : (String) -> Unit,
    description : String,
    onDescriptionTextChange : (String) -> Unit,
    modifier : Modifier = Modifier
){
    Box (
        modifier = modifier
            .fillMaxSize()
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

            TextField(
                modifier = Modifier
                    .fillMaxWidth(),
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
            Spacer(modifier = Modifier.height(20.dp))
            Divider(
                modifier = Modifier.alpha(ContentAlpha.medium),
                color = Color.LightGray,
                thickness = 1.dp
            )


            Spacer(modifier = Modifier.height(10.dp))
            TextField(
                modifier = Modifier
                    .fillMaxWidth(),
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
    val navController = rememberNavController()
    val item = BlogSearchItem(
        bloggerlink = "",
        bloggername = "민폐스러운 일상",
        title = "Happy New Year & Thanks",
        description = "(아까워서 못 까겠다.) Happy New Year, 라고 적기 전에 잡소리를 너무 길게 남긴 것 같아 좀 부끄럽다. 보기 싫은데 새 글 떠서 억지로 보게 될 이웃님들께는 그저 죄송할 따름이지만... 그냥 길고 힘들었던 2021년을... ",
        link = "https://blog.naver.com/sub_cat?Redirect=Log&logNo=222609846359",
        postdate = "20211231",
    )
    IntershipScrapProjectAndroidTheme {
        BlogScrapItem(
            item,
            "search",
            item.title,
            {},
            item.description,
            {}
        )
    }

}