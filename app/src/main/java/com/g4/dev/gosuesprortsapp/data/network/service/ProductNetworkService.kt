package com.g4.dev.gosuesprortsapp.data.network.service

import android.util.Log
import com.g4.dev.gosuesprortsapp.core.retrofit.RetrofitHelper
import com.g4.dev.gosuesprortsapp.data.model.response.ProductPageResponse
import com.g4.dev.gosuesprortsapp.data.network.interfaces.ProductApiClient
import com.g4.dev.gosuesprortsapp.data.network.interfaces.UsuarioApiClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ProductNetworkService {
    val retrofitHelper = RetrofitHelper.getRetrofit()

    suspend fun  getProductsByCategoria(idCategory: Int,pageNumber: Int, pageSize: Int):ProductPageResponse?{
        return  withContext(Dispatchers.IO){
            val response = retrofitHelper
                .create(ProductApiClient::class.java)
                .getProductsByIdCategoria( pageNumber, pageSize,idCategory)

            Log.i("Productos: ", response.body()!!.toString())

            response.body()
        }

    }
}