package com.g4.dev.gosuesprortsapp.core.retrofit

import com.g4.dev.gosuesprortsapp.GosueSportApplicationClass
import com.g4.dev.gosuesprortsapp.util.RetrofitUrlConstants
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitHelperNotice {
    fun getRetrofitNotice(): Retrofit {
        return  Retrofit.Builder()
            .baseUrl(RetrofitUrlConstants.BASE_URL_GAMES_API)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient())
            .build()
    }

    private fun okHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .readTimeout(40, TimeUnit.SECONDS)
            .connectTimeout(1, TimeUnit.MINUTES)
            .addInterceptor(NoticeInterceptor())
            .build()
    }




}

class  NoticeInterceptor():Interceptor{
    override fun intercept(chain: Interceptor.Chain): Response {
        val response = chain.request()
            .newBuilder()
            .addHeader("X-RapidAPI-Key", "65c1178e6amshfc57c88cb8e1d25p1ae479jsnd4ae93aa8a7d")
            .addHeader("X-RapidAPI-Host","videogames-news2.p.rapidapi.com")
            .build()


        return  chain.proceed(response)
    }

}