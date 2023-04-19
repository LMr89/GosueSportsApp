package com.g4.dev.gosuesprortsapp.data.network.service.payment

import com.g4.dev.gosuesprortsapp.core.retrofit.RetrofitHelper
import com.g4.dev.gosuesprortsapp.data.model.request.payment.ChargeRequest
import com.g4.dev.gosuesprortsapp.data.model.request.payment.CreditCardTokenRequest
import com.g4.dev.gosuesprortsapp.data.model.request.payment.YapeTokenRequest
import com.g4.dev.gosuesprortsapp.data.model.response.payment.ChargeResponse
import com.g4.dev.gosuesprortsapp.data.model.response.payment.MethodsPaymentTokenResponse
import com.g4.dev.gosuesprortsapp.data.network.interfaces.payment.PaymentApiClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PaymentService {
    val retrofit = RetrofitHelper.getRetrofit()

    suspend fun  createCreditCardTokenService(creditCardTokenRequest: CreditCardTokenRequest):MethodsPaymentTokenResponse?{
        return withContext(Dispatchers.IO){
                val response = retrofit
                    .create(PaymentApiClient::class.java)
                    .createCreditCardToken(creditCardTokenRequest)

            response.body()


        }
    }


    suspend fun  createYapeTokenService(yapeTokenRequest: YapeTokenRequest):MethodsPaymentTokenResponse?{
        return withContext(Dispatchers.IO){
            val response = retrofit
                .create(PaymentApiClient::class.java)
                .createYapeToken(yapeTokenRequest)

            response.body()


        }
    }

    suspend fun  createSaleChargeService(chargeRequest: ChargeRequest):ChargeResponse?{
        return withContext(Dispatchers.IO){
            val response = retrofit
                .create(PaymentApiClient::class.java)
                .createSaleCharge(chargeRequest)

            response.body()


        }
    }


}