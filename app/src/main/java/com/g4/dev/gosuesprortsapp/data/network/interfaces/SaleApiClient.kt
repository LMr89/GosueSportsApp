package com.g4.dev.gosuesprortsapp.data.network.interfaces

import com.g4.dev.gosuesprortsapp.data.model.request.sale.SaleRequest
import com.g4.dev.gosuesprortsapp.data.model.response.common.CommonResponseServer
import com.g4.dev.gosuesprortsapp.util.RetrofitUrlConstants
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface SaleApiClient {

    @POST(RetrofitUrlConstants.POST_NEW_SALE_URL)
    suspend fun  postNewSale(@Body saleRequest: SaleRequest):Response<CommonResponseServer>

}