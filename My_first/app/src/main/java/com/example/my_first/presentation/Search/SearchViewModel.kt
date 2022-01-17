package com.example.my_first.presentation.Search

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.my_first.domain.use_case.ScrapUseCases
import com.example.my_first.presentation.Search.SearchEvent
import com.example.my_first.presentation.Search.SearchState
import com.example.my_first.presentation.scrap.ScrapEvent
import com.example.my_first.presentation.scrap.ScrapState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
) : ViewModel() {

    private val _state = mutableStateOf(SearchState())
    val state: State<SearchState> = _state

    fun onEvent(event: SearchEvent) {
        when (event) {
            is SearchEvent.searchOrder -> {
                _state.value = state.value.copy(
                    isSearchOrderList = !state.value.isSearchOrderList
                )
            }
        }
    }


}