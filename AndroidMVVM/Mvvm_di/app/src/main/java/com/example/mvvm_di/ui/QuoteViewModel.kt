package com.example.mvvm_di.ui

import androidx.lifecycle.ViewModel
import com.example.mvvm_di.data.QuoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class QuoteViewModel @Inject constructor(
    private val repository: QuoteRepository
) : ViewModel(){
}