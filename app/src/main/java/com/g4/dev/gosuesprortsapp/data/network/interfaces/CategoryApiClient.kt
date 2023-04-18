package com.g4.dev.gosuesprortsapp.data.network.interfaces

import com.g4.dev.gosuesprortsapp.core.entity.Category
import com.g4.dev.gosuesprortsapp.util.RetrofitUrlConstants
import retrofit2.Response
import retrofit2.http.GET

interface CategoryApiClient {

    @GET(RetrofitUrlConstants.LIST_CATEGORIES_URL)
    suspend fun getAllCategories():Response<List<Category>>
}