package com.example.intership_scrapproject_android.domain.use_case

import com.example.intership_scrapproject_android.data.local.InvalidPostException
import com.example.intership_scrapproject_android.data.local.Post
import com.example.intership_scrapproject_android.data.local.PostRepository
import com.example.intership_scrapproject_android.data.local.Resource

class DeletePostUseCase(
    private val postRepository : PostRepository
) {
    suspend operator fun invoke(
        post : Post
    ) : Resource<Int> {
        return postRepository.deletePost(post)
    }
}