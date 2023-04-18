package com.g4.dev.gosuesprortsapp.core.retrofit

import android.content.SharedPreferences
import android.util.Log
import okhttp3.Interceptor
import okhttp3.Response

class ClientInterceptor(
    private val temporalShares:SharedPreferences
) : Interceptor {


    companion object{
        const val  TOKEN_ACCESS = "apiToken"
    }

    override fun intercept(chain: Interceptor.Chain): Response {

        val storedToken  = temporalShares.getString(TOKEN_ACCESS,"") ?:""

        val request = chain
            .request()
            .newBuilder()

        if (storedToken.isNotEmpty()){
            Log.i("Token: ",storedToken)
            request.addHeader("Authorization", "Bearer $storedToken")
        }

        return  chain.proceed(request.build())
    }
}