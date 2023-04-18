package com.g4.dev.gosuesprortsapp.data.network.service

import com.g4.dev.gosuesprortsapp.core.entity.Computer
import com.g4.dev.gosuesprortsapp.core.retrofit.RetrofitHelper
import com.g4.dev.gosuesprortsapp.data.network.interfaces.ComputerApiClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ComputerNetworkService {

    val retrofit  =RetrofitHelper.getRetrofit()

    suspend fun  getAllComputersService():List<Computer>?{
        return  withContext(Dispatchers.IO){
            val response = retrofit
                .create(ComputerApiClient::class.java)
                .getAllComputers()

            response.body()
        }
    }
}