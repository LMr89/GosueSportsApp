package com.g4.dev.gosuesprortsapp.ui.activity

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.g4.dev.gosuesprortsapp.data.model.request.AuthLoginRequest
import com.g4.dev.gosuesprortsapp.domain.authUseCases.AuthUserUseCase
import kotlinx.coroutines.launch

class LoginViewModel: ViewModel() {
    val isAuthenticationSuccess = MutableLiveData<Boolean>()
    var authUseCase = AuthUserUseCase()




    fun onSendForAuthentication(login:AuthLoginRequest){
        viewModelScope.launch {
            isAuthenticationSuccess.postValue(false)

            val response =  authUseCase(login)

            if (response != null) {
                isAuthenticationSuccess.postValue(true)
                Log.i("Respuesta", response.toString())

            }


        }
    }




}