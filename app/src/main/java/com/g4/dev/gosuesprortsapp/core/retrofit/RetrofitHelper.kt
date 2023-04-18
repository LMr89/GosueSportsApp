package com.g4.dev.gosuesprortsapp.core.retrofit

import com.g4.dev.gosuesprortsapp.GosueSportApplicationClass
import com.g4.dev.gosuesprortsapp.util.RetrofitUrlConstants
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitHelper {
    fun getRetrofit():Retrofit{
        return  Retrofit.Builder()
            .baseUrl(RetrofitUrlConstants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient())
            .build()
    }

    private fun okHttpClient():OkHttpClient{
        return OkHttpClient.Builder()
            .readTimeout(40, TimeUnit.SECONDS)
            .connectTimeout(1, TimeUnit.MINUTES)
            .addInterceptor(ClientInterceptor(GosueSportApplicationClass.SHARED_PREFERENCES_INSTANCE))
            .build()
    }
}