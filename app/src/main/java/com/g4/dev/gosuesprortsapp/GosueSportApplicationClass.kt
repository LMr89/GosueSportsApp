package com.g4.dev.gosuesprortsapp

import android.app.Application
import android.content.Context
import android.content.SharedPreferences

class GosueSportApplicationClass: Application() {

    private val SHARED_PREFERENCE_NAME = "GosueSportSharedPreference"

    companion object{
        lateinit var   SHARED_PREFERENCES_INSTANCE :SharedPreferences
    }


    override fun onCreate() {
        super.onCreate()
        SHARED_PREFERENCES_INSTANCE = getSharedPreferences(SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE)
    }
}