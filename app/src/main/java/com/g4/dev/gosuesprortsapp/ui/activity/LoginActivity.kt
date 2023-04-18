package com.g4.dev.gosuesprortsapp.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.g4.dev.gosuesprortsapp.MainActivity
import com.g4.dev.gosuesprortsapp.R
import com.g4.dev.gosuesprortsapp.data.model.request.AuthLoginRequest
import com.g4.dev.gosuesprortsapp.databinding.ActivityLoginBinding
import com.g4.dev.gosuesprortsapp.util.messages.MessageType
import com.g4.dev.gosuesprortsapp.util.messages.MessageUtil
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
        initObservers()
    }

    private fun init(){
        mBinding.btnLogin.setOnClickListener(this)
        mLoginViewModel = ViewModelProvider(this)[LoginViewModel::class.java]
    }

    private fun initObservers(){
        mLoginViewModel.isAuthenticationSuccess.observe(this) {
                startActivityFromResponse(it)
        }
        mLoginViewModel.isLoading.observe(this){
            mBinding.pgProgress.isVisible = it
        }
    }
    private fun startActivityFromResponse(success:Boolean){
        if (!success){
            MessageUtil.sendMessage(mBinding.root, "Credenciales incorrectas", MessageType.ERROR)
            return
        }

        startActivity(Intent(this, MainActivity::class.java))
    }

    fun validateUserInput():Boolean{
        return  mBinding.etUserameLogin.text!!.isNotEmpty() && mBinding.etPasswordLogin.text!!.isNotEmpty()
    }

    override fun onClick(v: View) {
        if (!validateUserInput()){
            MessageUtil.sendMessage(v, "Porfavor llena los datos correctamente", MessageType.WARNING)
            return
        }
        mLoginViewModel.onSendForAuthentication(
            AuthLoginRequest(mBinding.etUserameLogin.text.toString(),mBinding.etPasswordLogin.text.toString())

        )

    }
}