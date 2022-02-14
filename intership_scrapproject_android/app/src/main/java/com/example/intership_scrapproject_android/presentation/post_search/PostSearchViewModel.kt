package com.example.intership_scrapproject_android.presentation.post_search

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.intership_scrapproject_android.core.util.OrderType
import com.example.intership_scrapproject_android.data.local.Post
import com.example.intership_scrapproject_android.data.local.Status
import com.example.intership_scrapproject_android.domain.use_case.GetPostUseCase
import com.example.intership_scrapproject_android.domain.use_case.PostSearchUseCase
import com.example.intership_scrapproject_android.presentation.blog_search.BlogSearchEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
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
        val searchResult : MutableList<Post> = mutableListOf()
        getPostsJob?.cancel()
        getPostsJob = getPostsUseCase(OrderType.SCRAP_DATE).data
            ?.onEach { posts ->
                posts.forEach { post ->
                    if (post.title.contains(query)){
                        searchResult.add(post)
                    }
                    else if (post.description.contains(query)){
                        searchResult.add(post)
                    }
                }
                _state.value = state.value.copy(
                    postSearchResult = searchResult,
                )
            }
            ?.launchIn(viewModelScope)
    }

}