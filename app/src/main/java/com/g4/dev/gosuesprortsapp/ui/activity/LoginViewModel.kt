package com.g4.dev.gosuesprortsapp.ui.activity

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.g4.dev.gosuesprortsapp.GosueSportApplicationClass
import com.g4.dev.gosuesprortsapp.data.model.request.AuthLoginRequest
import com.g4.dev.gosuesprortsapp.domain.authUseCases.AuthUserUseCase
import com.g4.dev.gosuesprortsapp.util.GosueSportCommonConstants
import kotlinx.coroutines.launch

class LoginViewModel: ViewModel() {
    val isAuthenticationSuccess = MutableLiveData<Boolean>()
    val isLoading = MutableLiveData<Boolean>()
    var authUseCase = AuthUserUseCase()





    fun onSendForAuthentication(login:AuthLoginRequest){
        viewModelScope.launch {
            isLoading.postValue(true)
            val response =  authUseCase(login)

            if (response != null) {
                isLoading.postValue(true)
                isAuthenticationSuccess.postValue(true)

                GosueSportApplicationClass.JWT_MANGER
                    .saveToken(response.token)

                GosueSportApplicationClass.SHARED_PREFERENCE_MANAGER
                    .savePreferences(GosueSportCommonConstants.USERNAME_SHARED_PREFERENCE, response.nomUsuario)


                return@launch
            }
            isLoading.postValue(false)
            isAuthenticationSuccess.postValue(false)


        }
    }




}