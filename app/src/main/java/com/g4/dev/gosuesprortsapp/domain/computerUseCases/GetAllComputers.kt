package com.g4.dev.gosuesprortsapp.domain.computerUseCases

import com.g4.dev.gosuesprortsapp.core.entity.Computer
import com.g4.dev.gosuesprortsapp.data.model.response.news.NewsResponse
import com.g4.dev.gosuesprortsapp.data.network.service.ComputerNetworkService
import com.g4.dev.gosuesprortsapp.data.network.service.news.NewsNetworkService

class GetAllComputers(){

        val service = ComputerNetworkService()

        suspend operator  fun invoke():List<Computer>{
                return  service.getAllComputersService()!!
        }
}