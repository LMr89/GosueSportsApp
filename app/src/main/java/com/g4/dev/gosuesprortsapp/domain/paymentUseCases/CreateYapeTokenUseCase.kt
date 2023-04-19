package com.g4.dev.gosuesprortsapp.domain.paymentUseCases

import com.g4.dev.gosuesprortsapp.data.model.request.payment.YapeTokenRequest
import com.g4.dev.gosuesprortsapp.data.model.response.payment.MethodsPaymentTokenResponse
import com.g4.dev.gosuesprortsapp.data.network.service.payment.PaymentService

class CreateYapeTokenUseCase {
    val paymentService = PaymentService()

    suspend operator fun invoke(yapeTokenRequest: YapeTokenRequest): MethodsPaymentTokenResponse?{

        return  paymentService.createYapeTokenService(yapeTokenRequest)

    }

}