package com.g4.dev.gosuesprortsapp.data.network.interfaces

import com.g4.dev.gosuesprortsapp.data.model.response.ProductPageResponse
import com.g4.dev.gosuesprortsapp.util.RetrofitUrlConstants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ProductApiClient {
    @GET(RetrofitUrlConstants.LIST_PRODUCTS_PAGE_URL)
    suspend fun  getProductsByIdCategoria(@Query("pageNumber") pageNumber: Int ,
                                          @Query("pageSize")   pageSize :Int,
                                          @Query("idCategoria") idCategory: Int,):Response<ProductPageResponse>

}