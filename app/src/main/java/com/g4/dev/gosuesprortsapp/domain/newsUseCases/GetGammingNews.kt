package com.g4.dev.gosuesprortsapp.domain.newsUseCases

import com.g4.dev.gosuesprortsapp.data.model.response.news.NewsResponse
import com.g4.dev.gosuesprortsapp.data.network.service.news.NewsNetworkService

class GetGammingNews {
    val service = NewsNetworkService()

    suspend operator  fun invoke():List<NewsResponse>{
        return  service.getNewsService()!!
    }
}