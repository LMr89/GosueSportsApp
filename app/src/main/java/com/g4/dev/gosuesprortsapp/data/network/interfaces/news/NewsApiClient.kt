package com.g4.dev.gosuesprortsapp.data.network.interfaces.news

import com.g4.dev.gosuesprortsapp.data.model.response.news.NewsResponse
import com.g4.dev.gosuesprortsapp.util.RetrofitUrlConstants
import retrofit2.Response
import retrofit2.http.GET

interface NewsApiClient {

    @GET(RetrofitUrlConstants.RECENT_NOTICE_URL)
    suspend fun  getGammerNews() : Response<List<NewsResponse>>
}