package com.example.intership_scrapproject_android.presentation.webview

import android.annotation.SuppressLint
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.intership_scrapproject_android.presentation.post_search.PostSearchEvent
import com.example.intership_scrapproject_android.presentation.post_search.components.PostSearchAppBar
import com.example.intership_scrapproject_android.ui.theme.IntershipScrapProjectAndroidTheme
import com.example.intership_scrapproject_android.util.TestTags

@SuppressLint("SetJavaScriptEnabled")
@Composable
fun PostWebViewScreen(
    navController: NavHostController,
    link : String?
) {
    val scaffoldState = rememberScaffoldState()

    Scaffold(
        topBar = {
            PostWebViewAppBar(
                onBackPress = {navController.popBackStack()}
            )
        },
        scaffoldState = scaffoldState
    ){
        AndroidView(
            modifier = Modifier.testTag(TestTags.postWebView),
            factory = {
                WebView(it).apply {
                    webViewClient = WebViewClient()
                    settings.javaScriptEnabled = true
                    loadUrl(link?:"")
                }
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PostWebViewScreenPreview() {
    val navController = rememberNavController()
    IntershipScrapProjectAndroidTheme {
        PostWebViewScreen(navController,"")
    }
}