package com.example.intership_scrapproject_android.presentation.post_search.components

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.intership_scrapproject_android.presentation.main.bottom_bar.BottomBar
import com.example.intership_scrapproject_android.presentation.post_search.PostSearchEvent
import com.example.intership_scrapproject_android.presentation.post_search.PostSearchViewModel

@ExperimentalComposeUiApi
@Composable
fun PostSearchScreen(
    navController: NavHostController,
    viewModel : PostSearchViewModel = hiltViewModel()
){
    val state = viewModel.state.value
    val scaffoldState = rememberScaffoldState()

    Scaffold(
        topBar = {
            PostSearchAppBar(
                text = state.postSearchText,
                onTextChange = {viewModel.onEvent(PostSearchEvent.PostSearchQueryChange(it))},
                onSearchClicked = {viewModel.onEvent(PostSearchEvent.GetPostSearchResult(it))},
                onBackPressed = {navController.popBackStack()}
            )
        },
        bottomBar = {
            BottomBar(navController = navController)
        },
        scaffoldState = scaffoldState
    ) {

            Box (
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colors.onSurface)
            ) {
                if (state.postSearchResult.isEmpty()) {
                    Text(
                        modifier = Modifier
                            .align(Alignment.Center)
                            .padding(bottom = 40.dp),
                        text = "검색 결과가 없습니다."
                    )
                } else {
                    LazyColumn(
                        modifier = Modifier.fillMaxSize()
                    ) {
                        items(state.postSearchResult) { post ->
                            PostSearchItem(state.postSearchText,post)
                        }
                    }
                }
            }

        if (state.showToastMessage){
            viewModel.onEvent(PostSearchEvent.ShowErrorToastHandled)
            Toast.makeText(LocalContext.current,state.toastErrorMessage, Toast.LENGTH_LONG).show()
        }


    }
}