package com.g4.dev.gosuesprortsapp.data.network.interfaces

import com.g4.dev.gosuesprortsapp.data.model.request.sale.SaleRequest
import com.g4.dev.gosuesprortsapp.data.model.response.common.CommonResponseServer
import com.g4.dev.gosuesprortsapp.data.model.response.sale.SaleResponse
import com.g4.dev.gosuesprortsapp.util.RetrofitUrlConstants
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface SaleApiClient {

    @POST(RetrofitUrlConstants.POST_NEW_SALE_URL)
    suspend fun  postNewSale(@Body saleRequest: SaleRequest):Response<CommonResponseServer>


    @GET(RetrofitUrlConstants.GET_SALES_HISTORY_URL)
    suspend fun  getSalesById(@Query("userId") userId:Int):Response<List<SaleResponse>>

}