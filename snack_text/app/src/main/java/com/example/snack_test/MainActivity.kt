package com.example.snack_test

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.SpringSpec
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.os.ConfigurationCompat
import com.example.snack_test.ui.theme.Snack_testTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val isFavorite = remember{mutableStateOf(true)}//Boolean값으로 초기화
            Snack_testTheme {
                Greeting("Android")
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.TopEnd
                ){
                    IconButton(onClick = {isFavorite.value = !isFavorite.value}){
                        Icon(imageVector = if(isFavorite.value) Icons.Default.Favorite else Icons.Default.FavoriteBorder, contentDescription = "favorite")
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    val isFavorite = remember{mutableStateOf(true)}//Boolean값으로 초기화
    Snack_testTheme {
        Greeting("Android")
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.TopEnd
        ){
            IconButton(onClick = {isFavorite.value = !isFavorite.value}){
                Icon(imageVector = if(isFavorite.value) Icons.Default.Favorite else Icons.Default.FavoriteBorder, contentDescription = "favorite")
            }
        }
    }
}