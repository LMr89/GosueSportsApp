package com.g4.dev.gosuesprortsapp.domain.saleUseCase

import com.g4.dev.gosuesprortsapp.data.model.request.sale.SaleRequest
import com.g4.dev.gosuesprortsapp.data.model.response.common.CommonResponseServer
import com.g4.dev.gosuesprortsapp.data.network.service.SaleNetworkService
import retrofit2.Response


class NewSaleUseCase {
    val saleNetworkService = SaleNetworkService()

    suspend  operator fun  invoke(saleRequest: SaleRequest): CommonResponseServer? {
        return  saleNetworkService.postNewSaleService(saleRequest)

    }

}