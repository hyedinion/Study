package com.example.my_first.presentation.Search

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.my_first.data.remote.SearchRepository
import com.example.my_first.domain.use_case.GetSearch
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val repository: SearchRepository
) : ViewModel() {

    private var searchJob: Job? = null

    private val _state = mutableStateOf(SearchState())
    val state: State<SearchState> = _state

    fun onEvent(event: SearchEvent) {
        when (event) {
            is SearchEvent.searchOrder -> {
                _state.value = state.value.copy(
                    isSearchOrderList = !state.value.isSearchOrderList
                )
            }
            is SearchEvent.queryChange -> {
                _state.value = state.value.copy(
                    searchText = event.query
                )
            }
            is SearchEvent.searchQueryInsert -> {
                Log.d("viewmodel","start viewmodel")
                val getSearch =  GetSearch(repository)
                searchJob?.cancel()
                searchJob = viewModelScope.launch {

                    _state.value = state.value.copy(
                        searchList = getSearch(event.query).cachedIn(viewModelScope)
                    )
                    Log.d("viewmodel","finish viewmodel")

                }
            }
        }
    }

}