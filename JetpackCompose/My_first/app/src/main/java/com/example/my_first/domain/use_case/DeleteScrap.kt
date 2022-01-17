package com.example.my_first.domain.use_case

import com.example.my_first.domain.model.Scrap
import com.example.my_first.domain.repository.ScrapRepository

class DeleteScrap(
    private val repository: ScrapRepository
) {

    suspend operator fun invoke(scrap : Scrap){
        repository.deleteScrap(scrap)
    }
}