package com.g4.dev.gosuesprortsapp.core.sharedPreference

import android.content.SharedPreferences

class SharedPreferenceManager (val sharedPreferences: SharedPreferences){

    suspend fun  savePreferences(key:String , value:String){
        with(sharedPreferences.edit()){
            putString(key, value)
            apply()
        }
    }


    suspend fun  getPreference(key:String): String? {
        return  sharedPreferences.getString(key, "")
    }


}