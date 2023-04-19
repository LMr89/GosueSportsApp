package com.g4.dev.gosuesprortsapp.data.network.interfaces.payment

import com.g4.dev.gosuesprortsapp.data.model.request.payment.ChargeRequest
import com.g4.dev.gosuesprortsapp.data.model.request.payment.CreditCardTokenRequest
import com.g4.dev.gosuesprortsapp.data.model.request.payment.YapeTokenRequest
import com.g4.dev.gosuesprortsapp.data.model.response.payment.ChargeResponse
import com.g4.dev.gosuesprortsapp.data.model.response.payment.MethodsPaymentTokenResponse
import com.g4.dev.gosuesprortsapp.util.RetrofitUrlConstants
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface PaymentApiClient {

    @POST(RetrofitUrlConstants.CREDIT_CARD_TOKEN_URL)
    suspend fun  createCreditCardToken(@Body creditCardTokenRequest: CreditCardTokenRequest):Response<MethodsPaymentTokenResponse>

    @POST(RetrofitUrlConstants.YAPE_TOKEN_URL)
    suspend fun  createYapeToken(@Body yapeTokenRequest: YapeTokenRequest):Response<MethodsPaymentTokenResponse>

    @POST(RetrofitUrlConstants.CHARGE_TOKEN_URL)
    suspend fun createSaleCharge(@Body chargeRequest: ChargeRequest):Response<ChargeResponse>

}