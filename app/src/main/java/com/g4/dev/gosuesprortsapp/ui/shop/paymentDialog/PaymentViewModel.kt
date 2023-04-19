package com.g4.dev.gosuesprortsapp.ui.shop.paymentDialog

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.g4.dev.gosuesprortsapp.data.model.request.payment.ChargeRequest
import com.g4.dev.gosuesprortsapp.data.model.request.payment.CreditCardTokenRequest
import com.g4.dev.gosuesprortsapp.data.model.request.payment.YapeTokenRequest
import com.g4.dev.gosuesprortsapp.data.model.response.payment.ChargeResponse
import com.g4.dev.gosuesprortsapp.data.model.response.payment.MethodsPaymentTokenResponse
import com.g4.dev.gosuesprortsapp.domain.paymentUseCases.CreateCreditCardUseCase
import com.g4.dev.gosuesprortsapp.domain.paymentUseCases.CreateSaleChargeUseCase
import com.g4.dev.gosuesprortsapp.domain.paymentUseCases.CreateYapeTokenUseCase
import kotlinx.coroutines.launch

class PaymentViewModel:ViewModel() {

    val methodsResponse = MutableLiveData<MethodsPaymentTokenResponse?>()
    val chargerResponse = MutableLiveData<ChargeResponse?>()

    val isLoading = MutableLiveData<Boolean>()
    val isAuthorized = MutableLiveData<Boolean>()
    val isChargeSaleSuccess = MutableLiveData<Boolean>()
    val authorizeToken = MutableLiveData<String>()

    //Instance
    val creditCardTokenCreation = CreateCreditCardUseCase()
    val yapeTokenCreation = CreateYapeTokenUseCase()
    val chargeCreation = CreateSaleChargeUseCase()

    val isOnLocalPayment = MutableLiveData<Boolean>().apply {
        TemporalPaymentData.IS_ON_STORE_PAYMENT
    }


    fun  createCreditCardViewModel(creaditCardTokenRequest: CreditCardTokenRequest){
        viewModelScope.launch {
            isLoading.postValue(true)
            val response = creditCardTokenCreation(creaditCardTokenRequest)

            Log.i("CREDIT-CARD", response.toString())
            if (response!!.id != null){
                isAuthorized.postValue( true)
                authorizeToken.postValue(response.id)
                methodsResponse.postValue(response)
            }else{
                isAuthorized.postValue( false)
            }
            isLoading.postValue(false)
        }

    }
    fun  createYapeViewModel(yapeTokenRequest: YapeTokenRequest){
        viewModelScope.launch {
            isLoading.postValue(true)
            val response = yapeTokenCreation(yapeTokenRequest)
            if (response != null){
                isAuthorized.postValue( true)
                authorizeToken.postValue(response.id)
                methodsResponse.postValue(response)
            }else{
                isAuthorized.postValue(false)
            }
            isLoading.postValue(false)
        }

    }

    fun  createSaleChargeViewModel(chargeRequest: ChargeRequest){
        viewModelScope.launch {
            isLoading.postValue(true)
            val response = chargeCreation(chargeRequest)
            if (response != null && response.description == "Exito") {
                chargerResponse.postValue(response)
                isChargeSaleSuccess.postValue( true)
            }else{
                isChargeSaleSuccess.postValue( false)
            }

            isLoading.postValue(false)
        }

    }

    fun resetFields(){
        isAuthorized.postValue(false)
        isChargeSaleSuccess.postValue(false)
        authorizeToken.postValue("")

    }

    fun setPaymentOnStore(onStore : Boolean){
        isOnLocalPayment.postValue(onStore)
    }





}