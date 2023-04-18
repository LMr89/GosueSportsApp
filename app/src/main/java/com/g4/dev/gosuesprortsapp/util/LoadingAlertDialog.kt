package com.g4.dev.gosuesprortsapp.util

import android.content.Context
import android.view.LayoutInflater
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.g4.dev.gosuesprortsapp.GosueSportApplicationClass
import com.g4.dev.gosuesprortsapp.R
import com.g4.dev.gosuesprortsapp.databinding.LoadingLayoutBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class LoadingAlertDialog {
    val alertBinding =
        LoadingLayoutBinding.inflate(GosueSportApplicationClass.INSTANCE.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater)

     lateinit var alertDialogBuilder: MaterialAlertDialogBuilder

    init {
        Glide
            .with(alertBinding.root)
            .load(R.drawable.loading_game)
            .override(200, 200)
            .diskCacheStrategy(DiskCacheStrategy.DATA)
            .into(alertBinding.imgLoading)



    }

    fun showAlertLoading(context: Context){
        alertDialogBuilder = MaterialAlertDialogBuilder(context)
            .setView(alertBinding.root)
        alertDialogBuilder.show()
    }

    fun hideAlert(){

    }
}