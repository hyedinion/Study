package com.example.intership_scrapproject_android.presentation.post_detail.components

import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.intership_scrapproject_android.data.local.Post
import com.example.intership_scrapproject_android.presentation.main.bottom_bar.BottomBar
import com.example.intership_scrapproject_android.presentation.post_detail.PostDetailEvent
import com.example.intership_scrapproject_android.presentation.post_detail.PostDetailViewModel
import com.example.intership_scrapproject_android.ui.theme.IntershipScrapProjectAndroidTheme

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun PostDetailScreen(
    navController: NavHostController,
    post : Post?,
    viewModel : PostDetailViewModel = hiltViewModel()
){
    val state = viewModel.state.value
    val scaffoldState = rememberScaffoldState()

    Scaffold(
        topBar = {
            PostDetailAppBar(
                onBackPress = { navController.popBackStack() },
                onDeleteButtonPress = {
                    viewModel.onEvent(
                        PostDetailEvent.PostDeleteButtonClicked(
                            post = post?: Post(
                                title = "",
                                description = "",
                                link = "",
                                keyword = "",
                                scrapDate = "",
                                postDate = "",
                                bloggerName = "",
                                id = null
                            )
                        )
                    )
                },
                onLinkButtonPress = {
                    if (post == null || post.link.isEmpty() ){
                        viewModel.onEvent(PostDetailEvent.MoveScreenHandled)
                    }
                    else {
                        navController.currentBackStackEntry?.savedStateHandle?.set("link", post.link)
                        navController.navigate("postWebView")
                    }
                },
                onEditButtonPress = {
                    navController.currentBackStackEntry?.savedStateHandle?.set("post", post)
                    navController.navigate("postEdit")
                }
            )
        },
        bottomBar = {
            BottomBar(navController = navController)
        },
        scaffoldState = scaffoldState
    ) {
        PostDetailItem(
            item = post ?: Post(
                title = "",
                description = "",
                link = "",
                keyword = "",
                scrapDate = "",
                postDate = "",
                bloggerName = "",
                id = null
            ),
            keyword = post?.keyword?:""
        )
    }

    if (state.deletePostSuccess){
        navController.popBackStack()
        viewModel.onEvent(PostDetailEvent.MoveScreenHandled)
        Toast.makeText(LocalContext.current,"post??? ?????????????????????.", Toast.LENGTH_LONG).show()
    }

    if (state.showDeleteErrorToastMessage){
        viewModel.onEvent(PostDetailEvent.ShowDeleteErrorToastHandled)
        Toast.makeText(LocalContext.current,state.DeleteErrorToastMessage, Toast.LENGTH_LONG).show()
    }

    if (state.showEmptyLinkErrorToastMessage){
        viewModel.onEvent(PostDetailEvent.EmptyLinkToastHandled)
        Toast.makeText(LocalContext.current,state.EmptyLinkErrorToastMessage, Toast.LENGTH_LONG).show()
    }


}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun BlogSearchScreenPreview() {
    val navController = rememberNavController()

    val item = Post(
        title = "Happy New Year & Thanks",
        description = "(???????????? ??? ?????????.) Happy New Year, ?????? ?????? ?????? ???????????? ?????? ?????? ?????? ??? ?????? ??? ????????????. ?????? ????????? ??? ??? ?????? ????????? ?????? ??? ?????????????????? ?????? ????????? ???????????????... ?????? ?????? ???????????? 2021??????... ",
        link = "https://blog.naver.com/sub_cat?Redirect=Log&logNo=222609846359",
        keyword = "happy",
        scrapDate = "20211231",
        postDate = "20211231",
        bloggerName = "??????????????? ??????",
        id = null
    )

    IntershipScrapProjectAndroidTheme {
        PostDetailScreen(navController, item)
    }
}