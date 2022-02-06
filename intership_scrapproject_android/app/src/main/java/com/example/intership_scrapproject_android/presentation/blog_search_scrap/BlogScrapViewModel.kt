package com.example.intership_scrapproject_android.presentation.blog_search_scrap

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.intership_scrapproject_android.data.local.Post
import com.example.intership_scrapproject_android.data.local.Status
import com.example.intership_scrapproject_android.data.remote.response.BlogSearchItem
import com.example.intership_scrapproject_android.domain.use_case.InsertPostUseCase
import com.example.intership_scrapproject_android.presentation.blog_search.BlogSearchState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import javax.inject.Inject

@HiltViewModel
class BlogScrapViewModel @Inject constructor(
    private val insertPost : InsertPostUseCase
) : ViewModel() {

    private val _state = mutableStateOf(BlogScrapState())
    val state: State<BlogScrapState> = _state

    @RequiresApi(Build.VERSION_CODES.O)
    fun onEvent(event: BlogScrapEvent) {
        when (event) {
            is BlogScrapEvent.InitBlogScrapTitleDescription -> {
                _state.value = state.value.copy(
                    blogScrapTitle = event.title,
                    blogScrapDescription = event.description,
                    initBlogScrapPost = false
                )
            }
            is BlogScrapEvent.BlogScrapTitleChange -> {
                _state.value = state.value.copy(
                    blogScrapTitle = event.title
                )
            }
            is BlogScrapEvent.BlogScrapDescriptionChange -> {
                _state.value = state.value.copy(
                    blogScrapDescription = event.description
                )
            }
            is BlogScrapEvent.BlogScrapButtonClicked -> {
                viewModelScope.launch {
                    val newPost = blogSearchItemToPost(event.blogSearchItem,event.keyword)
                    try{
                        val insertPostResult = insertPost(newPost)
                        if(insertPostResult.status == Status.SUCCESS){
                            _state.value = state.value.copy(
                                saveBlogSuccess = true
                            )
                        }
                        else{
                            _state.value = state.value.copy(
                                insertErrorToastMessage = insertPostResult.message.toString(),
                                showInsertErrorToastMessage = true
                            )
                        }
                    }catch (e : Exception){
                        _state.value = state.value.copy(
                            blankErrorToastMessage = e.message.toString(),
                            showBlankToastMessage = true
                        )

                    }
                }
            }
            is BlogScrapEvent.ShowInsertErrorToastHandled -> {
                _state.value = state.value.copy(
                    showInsertErrorToastMessage = false
                )
            }
            is BlogScrapEvent.ShowBlankErrorToastHandled -> {
                _state.value = state.value.copy(
                    showBlankToastMessage = false
                )
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun blogSearchItemToPost(blogSearchItem : BlogSearchItem, keyword : String) : Post {
        val current = LocalDate.now()
        val formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd")
        val formatted = current.format(formatter)
        return Post(
            title = blogSearchItem.title,
            description = blogSearchItem.description,
            link = blogSearchItem.link,
            keyword = keyword,
            postDate = blogSearchItem.postdate,
            bloggerName = blogSearchItem.bloggername,
            scrapDate = formatted
        )
    }

}