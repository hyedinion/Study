package com.example.webview_example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.webview_example.ui.theme.WebView_exampleTheme
import com.example.webview_example.view.WebViewScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WebView_exampleTheme {
                WebViewScreen("naver.com")

            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    WebView_exampleTheme {

    }
}