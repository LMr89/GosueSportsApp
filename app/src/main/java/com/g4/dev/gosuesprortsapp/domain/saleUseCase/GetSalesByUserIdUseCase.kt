package com.g4.dev.gosuesprortsapp.domain.saleUseCase

import com.g4.dev.gosuesprortsapp.data.model.request.sale.SaleRequest
import com.g4.dev.gosuesprortsapp.data.model.response.common.CommonResponseServer
import com.g4.dev.gosuesprortsapp.data.model.response.sale.SaleResponse
import com.g4.dev.gosuesprortsapp.data.network.service.SaleNetworkService

class GetSalesByUserIdUseCase {
    val saleNetworkService = SaleNetworkService()

    suspend  operator fun  invoke(userId:Int): List<SaleResponse>? {
        return  saleNetworkService.getSalesByUserId(userId)

    }
}