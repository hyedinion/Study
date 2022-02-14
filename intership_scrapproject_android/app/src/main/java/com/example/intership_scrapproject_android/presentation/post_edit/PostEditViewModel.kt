package com.example.intership_scrapproject_android.presentation.post_edit

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.intership_scrapproject_android.data.local.Status
import com.example.intership_scrapproject_android.domain.use_case.InsertPostUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostEditViewModel @Inject constructor(
    private val insertPost : InsertPostUseCase
) : ViewModel() {

    private val _state = mutableStateOf(PostEditState())
    val state: State<PostEditState> = _state

    @RequiresApi(Build.VERSION_CODES.O)
    fun onEvent(event: PostEditEvent) {
        when (event) {
            is PostEditEvent.InitEditPost -> {
                _state.value = state.value.copy(
                    postEditTitle = event.post.title,
                    postEditDescription = event.post.description,
                    initEditPost = false
                )
            }
            is PostEditEvent.PostEditTitleChange -> {
                _state.value = state.value.copy(
                    postEditTitle = event.title
                )
            }
            is PostEditEvent.PostEditDescriptionChange -> {
                _state.value = state.value.copy(
                    postEditDescription = event.description
                )
            }
            is PostEditEvent.PostEditButtonClicked -> {
                viewModelScope.launch {
                    try{
                        val insertPostResult = insertPost(event.post)
                        if(insertPostResult.status == Status.SUCCESS){
                            _state.value = state.value.copy(
                                EditPostSuccess = true
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
            is PostEditEvent.ShowInsertErrorToastHandled -> {
                _state.value = state.value.copy(
                    showInsertErrorToastMessage = false
                )
            }
            is PostEditEvent.ShowBlankErrorToastHandled -> {
                _state.value = state.value.copy(
                    showBlankToastMessage = false
                )
            }
            is PostEditEvent.MoveScreenHandled -> {
                _state.value = state.value.copy(
                    EditPostSuccess = false
                )
            }
            is PostEditEvent.changeActivateMode -> {
                _state.value = state.value.copy(
                    editModeActivate = !state.value.editModeActivate
                )
            }
        }
    }

}