package com.example.intership_scrapproject_android.presentation.post.components

import android.widget.Toast
import androidx.compose.animation.*
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.intership_scrapproject_android.presentation.main.bottom_bar.BottomBar
import com.example.intership_scrapproject_android.presentation.post.PostEvent
import com.example.intership_scrapproject_android.presentation.post.PostViewModel

@ExperimentalMaterialApi
@ExperimentalFoundationApi
@ExperimentalAnimationApi
@Composable
fun PostScreen(
    navController: NavHostController,
    viewModel : PostViewModel = hiltViewModel()
){
    val state = viewModel.state.value
    val scaffoldState = rememberScaffoldState()

    if (state.initBlogScrapPostNeed){
        viewModel.onEvent(PostEvent.InitPostItem)
    }

    Scaffold(
        topBar = {
            PostAppBar(
                isOrderSectionVisible = state.isOrderSectionVisible,
                onOrderSectionButtonPress = { viewModel.onEvent(PostEvent.ToggleOrderSection) },
                onSearchButtonPress = { navController.navigate("postSearch")},
                orderType = state.postOrder
            )
        },
        bottomBar = {
            BottomBar(navController = navController)
        },
        scaffoldState = scaffoldState
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            AnimatedVisibility(
                visible = state.isOrderSectionVisible,
                enter = fadeIn() + slideInVertically(),
                exit = fadeOut() + slideOutVertically()

            ) {
                PostOrderSection(
                    orderType = state.postOrder,
                    onOrderChange = {
                        viewModel.onEvent(PostEvent.OrderChange(it)) //usecase에서 전달해준 order로 설정
                    }
                )
            }

            PostLazyColumn(
                navController = navController,
                state.postOrder,
                state.posts,
                dismissItem = {viewModel.onEvent(PostEvent.DeletePost(it))}
            )

        }
    }

    if (state.deletePostSuccess){
        viewModel.onEvent(PostEvent.deletePostHandled)
        Toast.makeText(LocalContext.current,"post를 삭제하였습니다.", Toast.LENGTH_LONG).show()
    }

    if (state.showDeleteErrorToastMessage){
        viewModel.onEvent(PostEvent.ShowDeleteErrorToastHandled)
        Toast.makeText(LocalContext.current,state.DeleteErrorToastMessage, Toast.LENGTH_LONG).show()
    }
}