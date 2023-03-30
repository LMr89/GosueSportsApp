package com.g4.dev.gosuesprortsapp.core.retrofit

import android.content.SharedPreferences
import okhttp3.Interceptor
import okhttp3.Response

class ClientInterceptor(
    private val temporalShares:SharedPreferences
) : Interceptor {

    private val TOKEN_ACCESS = "apiToken"

    override fun intercept(chain: Interceptor.Chain): Response {

        val storedToken  = temporalShares.getString(TOKEN_ACCESS,"") ?:""

        val request = chain
            .request()
            .newBuilder()

        if (storedToken.isNotEmpty()){
            request.addHeader("Authorization",storedToken)
        }

        return  chain.proceed(request.build())
    }
}