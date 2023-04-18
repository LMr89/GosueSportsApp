package com.g4.dev.gosuesprortsapp.data.network.service.news

import com.g4.dev.gosuesprortsapp.core.retrofit.RetrofitHelperNotice
import com.g4.dev.gosuesprortsapp.data.model.response.news.NewsResponse
import com.g4.dev.gosuesprortsapp.data.network.interfaces.news.NewsApiClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class NewsNetworkService {
    val client = RetrofitHelperNotice.getRetrofitNotice()


    suspend fun  getNewsService():List<NewsResponse>?{
        return  withContext(Dispatchers.IO){
            val response = client
                .create(NewsApiClient::class.java)
                .getGammerNews()

            response.body()
        }
    }




}