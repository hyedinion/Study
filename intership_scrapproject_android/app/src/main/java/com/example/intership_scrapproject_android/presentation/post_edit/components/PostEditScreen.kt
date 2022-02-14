package com.example.intership_scrapproject_android.presentation.post_edit.components

import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.intership_scrapproject_android.data.local.Post
import com.example.intership_scrapproject_android.presentation.main.bottom_bar.BottomBar
import com.example.intership_scrapproject_android.presentation.main.bottom_bar.BottomBarRoute
import com.example.intership_scrapproject_android.presentation.post_edit.PostEditEvent
import com.example.intership_scrapproject_android.presentation.post_edit.PostEditViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun PostEditScreen(
    navController: NavHostController,
    post : Post?,
    viewModel : PostEditViewModel = hiltViewModel()
){
    val state = viewModel.state.value
    val scaffoldState = rememberScaffoldState()
    val emptyPostItem = Post( "", "", "", "", "", "","")
    if (state.initEditPost) {
        viewModel.onEvent( PostEditEvent.InitEditPost( post?:emptyPostItem ) )
    }

    Scaffold(
        topBar = {
            PostEditAppBar(
                onBackPress = { navController.popBackStack() },
                onInsertButtonPress = {
                    post?.title = state.postEditTitle
                    post?.description = state.postEditDescription
                    viewModel.onEvent(
                        PostEditEvent.PostEditButtonClicked(
                            post = post ?: emptyPostItem
                        )
                    )
                },
                editModeActivate = state.editModeActivate
            )
        },
        bottomBar = {
            BottomBar(navController = navController)
        },
        scaffoldState = scaffoldState
    ) {
        PostEditItem(
            item = post ?: emptyPostItem,
            title = state.postEditTitle,
            onTitleTextChange = { viewModel.onEvent(PostEditEvent.PostEditTitleChange(it)) },
            description = state.postEditDescription,
            onDescriptionTextChange = {
                viewModel.onEvent( PostEditEvent.PostEditDescriptionChange(it) )
            },
        )
    }

    if (state.showInsertErrorToastMessage){
        viewModel.onEvent(PostEditEvent.ShowInsertErrorToastHandled)
        Toast.makeText(LocalContext.current,state.insertErrorToastMessage, Toast.LENGTH_LONG).show()
    }

    if (state.showBlankToastMessage){
        viewModel.onEvent(PostEditEvent.ShowBlankErrorToastHandled)
        Toast.makeText(LocalContext.current,state.blankErrorToastMessage, Toast.LENGTH_LONG).show()
    }

    if (state.EditPostSuccess){
        navController.popBackStack(BottomBarRoute.Post.route,false)
        viewModel.onEvent(PostEditEvent.MoveScreenHandled)
        Toast.makeText(LocalContext.current,"post가 수정되었습니다.", Toast.LENGTH_LONG).show()
    }

    if (!state.editModeActivate && post?.title != state.postEditTitle || post?.description != state.postEditDescription){
        viewModel.onEvent(PostEditEvent.changeActivateMode)
    }

    if (state.editModeActivate && post?.title == state.postEditTitle && post.description == state.postEditDescription){
        viewModel.onEvent(PostEditEvent.changeActivateMode)
    }


}