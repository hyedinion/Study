package com.example.intership_scrapproject_android.presentation.webview

import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.intership_scrapproject_android.ui.theme.IntershipScrapProjectAndroidTheme

@Composable
fun PostWebViewScreen(
    navController: NavHostController,
    link : String?
) {
    var formattedLink = link?:""
    if (link?.substring(8..11)=="blog") {
        formattedLink = link.substring(0..7) + "m." + link.substring(8)
    }

    AndroidView(
        factory = {
            WebView(it).apply {
                webViewClient = WebViewClient()
                loadUrl(formattedLink)
            }
        }
    )

}

@Preview(showBackground = true)
@Composable
fun PostWebViewScreenPreview() {
    val navController = rememberNavController()
    IntershipScrapProjectAndroidTheme {
        PostWebViewScreen(navController,"")
    }
}