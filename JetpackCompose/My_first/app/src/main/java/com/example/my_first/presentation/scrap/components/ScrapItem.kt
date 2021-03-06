package com.example.my_first.presentation.scrap.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.room.PrimaryKey
import com.example.my_first.domain.model.Scrap
import com.example.my_first.ui.theme.My_firstTheme
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Composable
fun ScrapItem(
    scrap : Scrap,
    modifier : Modifier = Modifier
){
    val dataFormat = SimpleDateFormat("yyyy.MM.dd")
    val postDate = dataFormat.format(scrap.postDate.toLong())
    Box (
        modifier = modifier
            .clickable{//webview호출

            }
    ){
        Text(
            modifier = modifier.align(Alignment.TopEnd),
            text = "읽음",
            style = MaterialTheme.typography.body2,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .padding(end = 32.dp)
        ) {
            Text(
                text = scrap.bloggerName,
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
            Text(
                text = scrap.title,
                style = MaterialTheme.typography.h6,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = scrap.discription,
                style = MaterialTheme.typography.body1,
                maxLines = 3,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
    Divider(color = Color.Black, thickness = 1.dp)

}

@Preview(showBackground = true)
@Composable
fun ScrapItemPreview() {
    val currentTime : Long = System.currentTimeMillis()
    val dataFormat = SimpleDateFormat("yyyy.MM.dd")
    val formatted = dataFormat.format(currentTime)



    println("Current: $formatted")
    val scrap = Scrap( title = "<b>Happy</b> New Year & Thanks",
        discription = "(아까워서 못 까겠다.) <b>Happy</b> New Year, 라고 적기 전에 잡소리를 너무 길게 남긴 것 같아 좀 부끄럽다. 보기 싫은데 새 글 떠서 억지로 보게 될 이웃님들께는 그저 죄송할 따름이지만... 그냥 길고 힘들었던 2021년을... ",
        link = "https://blog.naver.com/sub_cat?Redirect=Log&logNo=222609846359",
        keyword = "happy",
        scrapDate = formatted,
        postDate = "20211231",
        bloggerName = "민폐스러운 일상"
    )

    My_firstTheme {
        LazyColumn {
            item {  ScrapItem(scrap)}
            item {  ScrapItem(scrap)}
        }

    }
}