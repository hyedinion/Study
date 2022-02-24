package com.example.intership_scrapproject_android.domain.use_case

import com.example.intership_scrapproject_android.data.local.InvalidPostException
import com.example.intership_scrapproject_android.data.local.Post
import com.example.intership_scrapproject_android.data.local.PostRepository
import com.example.intership_scrapproject_android.data.local.Resource

class InsertPostUseCase(
    private val postRepository : PostRepository
) {
    suspend operator fun invoke(
        post : Post
    ) : Resource<Unit>{
        if (post.title.isBlank()){
            throw InvalidPostException("title은 비어있을 수 없습니다.")
        }
        if (post.description.isBlank()){
            throw InvalidPostException("description은 비어있을 수 없습니다.")
        }
        return postRepository.insertPost(post)
    }
}