package com.example.webview_example.view

import android.util.Log
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import com.example.webview_example.ui.theme.WebView_exampleTheme

@Composable
fun WebViewScreen(url : String){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
                .height(50.dp)
                .background(MaterialTheme.colors.primary)
        ) {
            Text(
                text = "WebView Page",
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp

            )
        }

        Column {
            AndroidView(
                factory = {
                    WebView(it).apply {
                        webViewClient = WebViewClient()
                        loadUrl(url)
                    }
                }
            )
        }


    }
}

@Preview(showBackground = true)
@Composable
fun WebViewPreview() {
    WebView_exampleTheme {
        WebViewScreen("")
    }
}