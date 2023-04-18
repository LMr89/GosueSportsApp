package com.g4.dev.gosuesprortsapp.domain.categoryUseCases

import com.g4.dev.gosuesprortsapp.core.entity.Category
import com.g4.dev.gosuesprortsapp.core.entity.Usuario
import com.g4.dev.gosuesprortsapp.data.network.service.CategoryNetworkService

class GetAllCategories {
    private val categoryNetworkService = CategoryNetworkService()

    suspend operator fun invoke(): List<Category> {
        return categoryNetworkService.getAllCategories()!!
    }
}