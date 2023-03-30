package com.g4.dev.gosuesprortsapp.domain.authUseCases

import com.g4.dev.gosuesprortsapp.data.model.request.AuthLoginRequest
import com.g4.dev.gosuesprortsapp.data.model.response.AuthResponse
import com.g4.dev.gosuesprortsapp.data.network.service.AuthService

class AuthUserUseCase {
    private val authService = AuthService()
    suspend operator fun invoke(log: AuthLoginRequest): AuthResponse? {
        return authService.authenticateUser(log)
    }
}