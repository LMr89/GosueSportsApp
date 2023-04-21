package com.g4.dev.gosuesprortsapp.util.messages

import android.view.View
import androidx.core.content.ContextCompat
import com.g4.dev.gosuesprortsapp.GosueSportApplicationClass
import com.g4.dev.gosuesprortsapp.R
import com.google.android.material.snackbar.Snackbar

object MessageUtil {
    fun  sendMessage(v : View, msg:String, type:MessageType){
        val snackbar = Snackbar.make(v, msg, Snackbar.LENGTH_LONG)
        val view: View = snackbar.view

        if (type == MessageType.ERROR){
            snackbar.setBackgroundTint(ContextCompat.getColor(GosueSportApplicationClass.INSTANCE, R.color.snackbarerror))
        }
        if (type == MessageType.SUCCESS){
            snackbar.setBackgroundTint(ContextCompat.getColor(GosueSportApplicationClass.INSTANCE, R.color.snackbarexito))
        }
        if (type == MessageType.WARNING){
            snackbar.setBackgroundTint(ContextCompat.getColor(GosueSportApplicationClass.INSTANCE, R.color.snackbaradvertencia))
        }

        snackbar.show()

    }


}