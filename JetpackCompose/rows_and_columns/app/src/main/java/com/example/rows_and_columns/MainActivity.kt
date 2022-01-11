package com.example.rows_and_columns

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.rows_and_columns.ui.theme.Rows_and_columnsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Rows_and_columnsTheme {

            }
        }
    }
}


@Composable
fun RowScope.CustomItem(weight : Float, color : Color = MaterialTheme.colors.primary){ //ColumnScope안에서 사용할 composable이다.
    androidx.compose.material.Surface(//가로200, 세로 50의 box를 생성
        modifier = Modifier
            .width(50.dp)
            .height(50.dp)
            .weight(weight),
        color = color
    ) {}
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    Rows_and_columnsTheme {//theme은 전체 화면을 차지
        Row(
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.Start
        ) {
            CustomItem(weight = 3f, color = MaterialTheme.colors.secondary)
            CustomItem(weight = 1f)
        }

    }
}