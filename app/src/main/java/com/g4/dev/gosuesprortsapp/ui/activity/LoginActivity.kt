package com.g4.dev.gosuesprortsapp.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import com.g4.dev.gosuesprortsapp.MainActivity
import com.g4.dev.gosuesprortsapp.R
import com.g4.dev.gosuesprortsapp.data.model.request.AuthLoginRequest
import com.g4.dev.gosuesprortsapp.databinding.ActivityLoginBinding
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class LoginActivity : AppCompatActivity(), OnClickListener{
    private lateinit var  mBinding : ActivityLoginBinding
    private  lateinit var  mLoginViewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        init()
    }

    private fun init(){
        mBinding.btnLogin.setOnClickListener(this)
        mLoginViewModel = ViewModelProvider(this)[LoginViewModel::class.java]
    }

    override fun onClick(v: View?) {
        mLoginViewModel.onSendForAuthentication(AuthLoginRequest("Elios222222","12233312"))

    }
}