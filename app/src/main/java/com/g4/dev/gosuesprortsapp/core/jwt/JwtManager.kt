package com.g4.dev.gosuesprortsapp.core.jwt

import android.content.SharedPreferences
import com.g4.dev.gosuesprortsapp.core.retrofit.ClientInterceptor

class JwtManager(val sharedPreferences: SharedPreferences) {

        suspend  fun getToken():String{
            val token = sharedPreferences.getString(ClientInterceptor.TOKEN_ACCESS, "") ?:""
            return  token
        }

        fun saveToken( token:String){
            with(sharedPreferences.edit()){
                putString(ClientInterceptor.TOKEN_ACCESS, token)
                apply()
            }
        }



}