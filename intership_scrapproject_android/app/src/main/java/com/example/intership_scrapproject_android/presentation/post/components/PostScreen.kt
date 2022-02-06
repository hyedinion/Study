package com.example.intership_scrapproject_android.presentation.post.components

import android.widget.Toast
import androidx.compose.animation.*
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.intership_scrapproject_android.presentation.main.bottom_bar.BottomBar
import com.example.intership_scrapproject_android.presentation.post.PostEvent
import com.example.intership_scrapproject_android.presentation.post.PostViewModel

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
                onSearchButtonPress = { },
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

            LazyColumn(
                modifier = Modifier.fillMaxSize()
            ) {
                items(state.posts) { post ->
                    PostItem(
                        post
                    )

                }
            }
        }


    }



}