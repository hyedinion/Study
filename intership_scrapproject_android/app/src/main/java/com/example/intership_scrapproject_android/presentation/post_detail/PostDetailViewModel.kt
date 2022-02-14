package com.example.intership_scrapproject_android.presentation.post_detail

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.intership_scrapproject_android.data.local.Post
import com.example.intership_scrapproject_android.data.local.Status
import com.example.intership_scrapproject_android.domain.use_case.DeletePostUseCase
import com.example.intership_scrapproject_android.presentation.blog_search_scrap.BlogScrapEvent
import com.example.intership_scrapproject_android.presentation.blog_search_scrap.BlogScrapState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostDetailViewModel @Inject constructor(
    private val deletePost : DeletePostUseCase
) : ViewModel() {

    private val _state = mutableStateOf(PostDetailState())
    val state: State<PostDetailState> = _state

    @RequiresApi(Build.VERSION_CODES.O)
    fun onEvent(event: PostDetailEvent) {
        when (event) {
            is PostDetailEvent.PostDeleteButtonClicked -> {
                viewModelScope.launch {
                    try {
                        val deletePostResult = deletePost(event.post)
                        if (deletePostResult.status == Status.SUCCESS) {
                            _state.value = state.value.copy(
                                deletePostSuccess = true
                            )
                        } else {
                            _state.value = state.value.copy(
                                DeleteErrorToastMessage = deletePostResult.message.toString(),
                                showDeleteErrorToastMessage = true
                            )
                        }
                    } catch (e: Exception) {
                        _state.value = state.value.copy(
                            DeleteErrorToastMessage = e.message.toString(),
                            showDeleteErrorToastMessage = true
                        )

                    }
                }

            }
            is PostDetailEvent.ShowDeleteErrorToastHandled -> {
                _state.value = state.value.copy(
                    DeleteErrorToastMessage = "",
                    showDeleteErrorToastMessage = false
                )
            }
            is PostDetailEvent.MoveScreenHandled -> {
                _state.value = state.value.copy(
                    deletePostSuccess = false
                )
            }
            is PostDetailEvent.EmptyLinkDetect -> {
                _state.value = state.value.copy(
                    showEmptyLinkErrorToastMessage = true
                )
            }
            is PostDetailEvent.EmptyLinkToastHandled -> {
                _state.value = state.value.copy(
                    showEmptyLinkErrorToastMessage = false
                )
            }
        }

    }
}