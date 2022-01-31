package com.example.intership_scrapproject_android.presentation.blog_search

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.intership_scrapproject_android.domain.use_case.BlogSearchUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BlogSearchViewModel @Inject constructor(
    private val blogSearch : BlogSearchUseCase
) : ViewModel() {

    private var blogSearchJob: Job? = null

    private val _state = mutableStateOf(BlogSearchState())
    val state: State<BlogSearchState> = _state

    fun onEvent(event: BlogSearchEvent) {
        when (event) {
            is BlogSearchEvent.BlogSearchLayoutChange -> {
                _state.value = state.value.copy(
                    isBlogSearchLayoutList = !state.value.isBlogSearchLayoutList
                )
            }
            is BlogSearchEvent.BlogSearchQueryChange -> {
                _state.value = state.value.copy(
                    blogSearchText = event.query
                )
            }
            is BlogSearchEvent.GetBlogSearchResult -> {
                blogSearchJob?.cancel()
                blogSearchJob = viewModelScope.launch {
                    _state.value = state.value.copy(
                        blogSearchResult = blogSearch(event.query).cachedIn(viewModelScope)
                    )
                }
            }
            is BlogSearchEvent.Refreshing -> {
                _state.value = state.value.copy(
                    isRefreshing = !state.value.isRefreshing
                )
            }
        }
    }

}