package com.g4.dev.gosuesprortsapp.data.network.service

import com.g4.dev.gosuesprortsapp.core.retrofit.RetrofitHelper
import com.g4.dev.gosuesprortsapp.data.model.request.sale.SaleRequest
import com.g4.dev.gosuesprortsapp.data.model.response.common.CommonResponseServer
import com.g4.dev.gosuesprortsapp.data.network.interfaces.SaleApiClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response


class SaleNetworkService {
    val retrofinHelper = RetrofitHelper.getRetrofit()


    suspend fun  postNewSaleService(saleRequest: SaleRequest): CommonResponseServer? {
        return  withContext(Dispatchers.IO){
           val response =  retrofinHelper
                .create(SaleApiClient::class.java)
                .postNewSale(saleRequest)

        response.body()
        }
    }

}