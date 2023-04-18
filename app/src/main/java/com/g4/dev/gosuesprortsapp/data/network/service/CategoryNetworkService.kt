package com.g4.dev.gosuesprortsapp.data.network.service

import com.g4.dev.gosuesprortsapp.core.entity.Category
import com.g4.dev.gosuesprortsapp.core.entity.Usuario
import com.g4.dev.gosuesprortsapp.core.retrofit.RetrofitHelper
import com.g4.dev.gosuesprortsapp.data.network.interfaces.CategoryApiClient
import com.g4.dev.gosuesprortsapp.data.network.interfaces.UsuarioApiClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CategoryNetworkService {
    private val userRetrofit = RetrofitHelper.getRetrofit()

    suspend fun getAllCategories(): List<Category>?{
        return  withContext(Dispatchers.IO){
            val response = userRetrofit
                .create(CategoryApiClient::class.java)
                .getAllCategories()

            response.body()
        }

    }


}