package com.g4.dev.gosuesprortsapp

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.g4.dev.gosuesprortsapp.core.jwt.JwtManager
import com.g4.dev.gosuesprortsapp.core.sharedPreference.SharedPreferenceManager
import com.g4.dev.gosuesprortsapp.util.LoadingAlertDialog

class GosueSportApplicationClass: Application() {

    private val SHARED_PREFERENCE_NAME = "GosueSportSharedPreference"

    init {
        INSTANCE  = this
    }

    companion object{
        lateinit var   SHARED_PREFERENCES_INSTANCE :SharedPreferences
        lateinit var  INSTANCE:GosueSportApplicationClass
        lateinit var  JWT_MANGER : JwtManager
        lateinit var  SHARED_PREFERENCE_MANAGER: SharedPreferenceManager
        lateinit var  LOADING_ALERT_DIALOG:LoadingAlertDialog

    }


    override fun onCreate() {
        super.onCreate()
        SHARED_PREFERENCES_INSTANCE = getSharedPreferences(SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE)
        INSTANCE = this
        JWT_MANGER = JwtManager(SHARED_PREFERENCES_INSTANCE)
        SHARED_PREFERENCE_MANAGER  = SharedPreferenceManager(SHARED_PREFERENCES_INSTANCE)
        LOADING_ALERT_DIALOG = LoadingAlertDialog()
    }
}