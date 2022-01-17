package com.example.my_first.domain.use_case

import com.example.my_first.domain.model.Scrap
import com.example.my_first.domain.repository.ScrapRepository

class AddScrap(
    private val repository: ScrapRepository
) {

    suspend operator fun invoke(scrap: Scrap){
        if(scrap.title.isBlank()){
            throw IllegalArgumentException("title을 입력하세요")
        }
        repository.insertScrap(scrap)
    }
}