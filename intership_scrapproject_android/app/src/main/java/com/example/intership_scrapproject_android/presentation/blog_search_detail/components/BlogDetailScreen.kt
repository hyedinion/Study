package com.example.intership_scrapproject_android.presentation.blog_search_detail.components

import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.intership_scrapproject_android.data.remote.response.BlogSearchItem
import com.example.intership_scrapproject_android.presentation.main.bottom_bar.BottomBar
import com.example.intership_scrapproject_android.ui.theme.IntershipScrapProjectAndroidTheme

@Composable
fun BlogDetailScreen(
    navController: NavHostController,
    blog: BlogSearchItem?,
    keyword: String?,
){
    val scaffoldState = rememberScaffoldState()

    Scaffold(
        topBar = {
            BlogDetailAppBar(
                onBackPress = {navController.popBackStack()},
                onScrapButtonPress = {
                    navController.currentBackStackEntry?.savedStateHandle?.set("blog",blog)
                    navController.currentBackStackEntry?.savedStateHandle?.set("keyword",keyword)
                    navController.navigate("blogSearchScrap")
                 },
            )
        },
        bottomBar = {
            BottomBar(navController = navController)
        },
        scaffoldState = scaffoldState
    ) {
        BlogDetailItem(
            item = blog ?: BlogSearchItem(
                bloggerlink = "",
                bloggername = "",
                title = "",
                description = "",
                link = "",
                postdate = "",
            ),
            keyword = keyword ?: ""
        )
    }


}

@Preview(showBackground = true)
@Composable
fun BlogSearchScreenPreview() {
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
        BlogDetailScreen(navController, item, "search")
    }
}