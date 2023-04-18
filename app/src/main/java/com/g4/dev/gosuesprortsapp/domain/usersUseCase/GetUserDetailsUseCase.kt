package com.g4.dev.gosuesprortsapp.domain.usersUseCase

import com.g4.dev.gosuesprortsapp.core.entity.Usuario
import com.g4.dev.gosuesprortsapp.data.model.request.AuthLoginRequest
import com.g4.dev.gosuesprortsapp.data.model.response.AuthResponse
import com.g4.dev.gosuesprortsapp.data.network.service.AuthService
import com.g4.dev.gosuesprortsapp.data.network.service.UsuarioNetworkService

class GetUserDetailsUseCase {
    private val userNetworkService = UsuarioNetworkService()


    suspend operator fun invoke(username:String): Usuario? {
        return userNetworkService.getUserDetails(username)
    }
}