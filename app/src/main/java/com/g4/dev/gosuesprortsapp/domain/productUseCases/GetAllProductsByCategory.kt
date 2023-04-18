package com.g4.dev.gosuesprortsapp.domain.productUseCases

import com.g4.dev.gosuesprortsapp.core.entity.Usuario
import com.g4.dev.gosuesprortsapp.data.model.response.ProductPageResponse
import com.g4.dev.gosuesprortsapp.data.network.service.ProductNetworkService
import com.g4.dev.gosuesprortsapp.data.network.service.UsuarioNetworkService

class GetAllProductsByCategory {
    private val productNtService = ProductNetworkService()


    suspend operator fun invoke(idCategory: Int,pageNumber: Int, pageSize: Int): ProductPageResponse? {
        return productNtService.getProductsByCategoria( idCategory, pageNumber, pageSize)
    }

}