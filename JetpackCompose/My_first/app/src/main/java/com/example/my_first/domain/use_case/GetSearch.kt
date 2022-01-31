package com.example.my_first.domain.use_case

import android.util.Log
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.my_first.data.remote.Item
import com.example.my_first.data.remote.SearchRepository
import kotlinx.coroutines.flow.Flow

class GetSearch(
    private val repository: SearchRepository
) {
    operator fun invoke(
        query : String
    ) :  Flow<PagingData<Item>> {
        Log.d("usecase","usecase")
        return repository.getSearchResults(query)
    }

}