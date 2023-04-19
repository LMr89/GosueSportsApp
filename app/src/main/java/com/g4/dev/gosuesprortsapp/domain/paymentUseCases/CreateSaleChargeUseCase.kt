package com.g4.dev.gosuesprortsapp.domain.paymentUseCases

import com.g4.dev.gosuesprortsapp.data.model.request.payment.ChargeRequest
import com.g4.dev.gosuesprortsapp.data.model.response.payment.ChargeResponse
import com.g4.dev.gosuesprortsapp.data.network.service.payment.PaymentService

class CreateSaleChargeUseCase {
    val paymentService = PaymentService()

    suspend operator fun invoke(chargeRequest: ChargeRequest): ChargeResponse?{

        return  paymentService.createSaleChargeService(chargeRequest)

    }

}