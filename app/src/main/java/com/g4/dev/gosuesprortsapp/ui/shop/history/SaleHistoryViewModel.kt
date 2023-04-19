package com.g4.dev.gosuesprortsapp.ui.shop.history

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.g4.dev.gosuesprortsapp.GosueSportApplicationClass
import com.g4.dev.gosuesprortsapp.data.model.response.sale.SaleResponse
import com.g4.dev.gosuesprortsapp.domain.saleUseCase.GetSalesByUserIdUseCase
import com.g4.dev.gosuesprortsapp.util.GosueSportCommonConstants
import kotlinx.coroutines.launch

class SaleHistoryViewModel : ViewModel() {
    val saleList = MutableLiveData<List<SaleResponse>?>()
    val isLoadingSales= MutableLiveData<Boolean>()


   private  val getSalesByUserId = GetSalesByUserIdUseCase()

    fun getAllSales(){
        viewModelScope.launch {
            isLoadingSales.postValue(true)
            val userId  = GosueSportApplicationClass
                .SHARED_PREFERENCE_MANAGER
                .getPreference(GosueSportCommonConstants.USER_ID_PREFERENCE)!!

            val response = getSalesByUserId(userId.toInt())
            saleList.postValue(response)
            isLoadingSales.postValue(false)
        }
    }






}