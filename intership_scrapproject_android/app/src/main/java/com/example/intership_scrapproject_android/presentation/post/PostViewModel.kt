package com.example.intership_scrapproject_android.presentation.post

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.intership_scrapproject_android.core.util.OrderType
import com.example.intership_scrapproject_android.data.local.Status
import com.example.intership_scrapproject_android.domain.use_case.DeletePostUseCase
import com.example.intership_scrapproject_android.domain.use_case.GetPostUseCase
import com.example.intership_scrapproject_android.presentation.post_detail.PostDetailEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostViewModel @Inject constructor(
    private val getPostsUseCase : GetPostUseCase,
    private val deletePostUseCase: DeletePostUseCase
) : ViewModel() {

    private val _state = mutableStateOf(PostState())
    val state: State<PostState> = _state

    private var getPostsJob : Job? = null

    fun onEvent(event: PostEvent) {
        when (event) {
            is PostEvent.InitPostItem -> {
                getPosts(state.value.postOrder)
                _state.value = state.value.copy(
                    initBlogScrapPostNeed = false
                )
            }
            is PostEvent.OrderChange -> {
                if (event.orderType == state.value.postOrder) return
                getPosts(event.orderType)
            }
            is PostEvent.ToggleOrderSection -> {
                _state.value = state.value.copy(
                    isOrderSectionVisible = !state.value.isOrderSectionVisible
                )
            }
            is PostEvent.DeletePost -> {
                viewModelScope.launch {
                    try {
                        val deletePostResult = deletePostUseCase(event.post)
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
            is PostEvent.ShowDeleteErrorToastHandled -> {
                _state.value = state.value.copy(
                    DeleteErrorToastMessage = "",
                    showDeleteErrorToastMessage = false
                )
            }
            is PostEvent.deletePostHandled -> {
                _state.value = state.value.copy(
                    deletePostSuccess = false
                )
            }
        }
    }

    private fun getPosts(postOrder: OrderType){
        getPostsJob?.cancel()
        getPostsJob = getPostsUseCase(postOrder).data
            ?.onEach { posts ->
                _state.value = state.value.copy(
                    posts = posts,
                    postOrder = postOrder
                )
                Log.d("hi테스트","hi")
            }
            ?.launchIn(viewModelScope)

    }
}