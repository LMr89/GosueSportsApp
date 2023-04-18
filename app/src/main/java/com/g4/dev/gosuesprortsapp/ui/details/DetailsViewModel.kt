package com.g4.dev.gosuesprortsapp.ui.details

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.g4.dev.gosuesprortsapp.GosueSportApplicationClass
import com.g4.dev.gosuesprortsapp.core.entity.Usuario
import com.g4.dev.gosuesprortsapp.core.sharedPreference.SharedPreferenceManager
import com.g4.dev.gosuesprortsapp.domain.usersUseCase.GetUserDetailsUseCase
import com.g4.dev.gosuesprortsapp.util.GosueSportCommonConstants
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailsViewModel : ViewModel() {

    val userResponse = MutableLiveData<Usuario>()
    val getUserDetailsUseCase = GetUserDetailsUseCase()
    val isLoading = MutableLiveData<Boolean>()

    fun getUserDetails(){
        viewModelScope.launch {
            isLoading.postValue(true)
            val username  = GosueSportApplicationClass
                .SHARED_PREFERENCE_MANAGER
                .getPreference(GosueSportCommonConstants.USERNAME_SHARED_PREFERENCE)!!

            Log.i("NomUsuario", username)

            val userRes = getUserDetailsUseCase(username)
            GosueSportApplicationClass
                .SHARED_PREFERENCE_MANAGER
                .savePreferences(GosueSportCommonConstants.USER_ID_PREFERENCE, userRes!!.id.toString())
            userResponse.postValue(
                userRes!!
            )
            isLoading.postValue(false)
        }
    }






}