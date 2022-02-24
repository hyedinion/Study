package com.example.intership_scrapproject_android.presentation.post_search

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.intership_scrapproject_android.core.util.OrderType
import com.example.intership_scrapproject_android.data.local.Post
import com.example.intership_scrapproject_android.domain.use_case.GetPostUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class PostSearchViewModel @Inject constructor(
    private val getPostsUseCase : GetPostUseCase
) : ViewModel() {


    private val _state = mutableStateOf(PostSearchState())
    val state: State<PostSearchState> = _state

    private var getPostsJob : Job? = null

    fun onEvent(event: PostSearchEvent) {
        when (event) {
            is PostSearchEvent.PostSearchQueryChange -> {
                _state.value = state.value.copy(
                    postSearchText = event.query
                )
            }
            is PostSearchEvent.GetPostSearchResult -> {
                getPosts(event.query)
            }
            is PostSearchEvent.ShowErrorToastHandled -> {
                _state.value = state.value.copy(
                    showToastMessage = false
                )
            }
        }
    }

    private fun getPosts(query : String){
        getPostsJob?.cancel()
        getPostsJob = getPostsUseCase(OrderType.SCRAP_DATE).data
            ?.filter {
                it[0].title.contains(query) || it[0].description.contains(query)
            }
            ?.onEach { posts ->
                _state.value = state.value.copy(
                    postSearchResult = posts
                )
            }
            ?.launchIn(viewModelScope)
    }

}