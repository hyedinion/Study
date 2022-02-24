package com.example.intership_scrapproject_android.presentation.post

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.intership_scrapproject_android.util.OrderType
import com.example.intership_scrapproject_android.domain.use_case.GetPostUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class PostViewModel @Inject constructor(
    private val getPostsUseCase : GetPostUseCase
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
            }
            ?.launchIn(viewModelScope)
    }
}