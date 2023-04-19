package com.g4.dev.gosuesprortsapp.domain.paymentUseCases

import com.g4.dev.gosuesprortsapp.data.model.request.payment.CreditCardTokenRequest
import com.g4.dev.gosuesprortsapp.data.model.response.payment.MethodsPaymentTokenResponse
import com.g4.dev.gosuesprortsapp.data.network.service.payment.PaymentService

class CreateCreditCardUseCase {

    val paymentService = PaymentService()

    suspend operator fun invoke(creditCardTokenRequest: CreditCardTokenRequest):MethodsPaymentTokenResponse?{

        return  paymentService.createCreditCardTokenService(creditCardTokenRequest)

    }



}