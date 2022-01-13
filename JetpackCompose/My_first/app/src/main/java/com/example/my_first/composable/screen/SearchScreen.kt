package com.example.my_first.composable.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.my_first.ui.theme.My_firstTheme

@Composable
fun SearchListScreen(){
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)//item간의 간격
    ){
        items(50){i->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                shape = RoundedCornerShape(8.dp),
                elevation = 5.dp
            ) {
                Box(
                    modifier = Modifier.height(100.dp)
                ) {
//                    Image(
//                        painter = painterResource(id = R.drawable.test.png),
//                        contentDescription = "poster",
//                        contentScale = ContentScale.Crop
//                    )
                }
            }
        }
    }
}

@Composable
fun SearchGridScreen(){
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)//item간의 간격
    ){
        items(10){
            Row(
                modifier = Modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.Start
            ) {
                Card(
                    modifier = Modifier
                        .weight(0.5f)
                        .padding(16.dp),
                    shape = RoundedCornerShape(8.dp),
                    elevation = 5.dp
                ) {
                    Box(
                        modifier = Modifier.height(200.dp)
                    ) {
//                    Image(
//                        painter = painterResource(id = R.drawable.test.png),
//                        contentDescription = "poster",
//                        contentScale = ContentScale.Crop
//                    )
                    }
                }
                Card(
                    modifier = Modifier
                        .weight(0.5f)
                        .padding(16.dp),
                    shape = RoundedCornerShape(8.dp),
                    elevation = 5.dp
                ) {
                    Box(
                        modifier = Modifier.height(200.dp)
                    ) {
//                    Image(
//                        painter = painterResource(id = R.drawable.test.png),
//                        contentDescription = "poster",
//                        contentScale = ContentScale.Crop
//                    )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ScreenListPreview() {
    My_firstTheme {
        SearchListScreen()
    }
}

@Preview(showBackground = true)
@Composable
fun ScreenGridPreview() {
    My_firstTheme {
        SearchGridScreen()
    }
}