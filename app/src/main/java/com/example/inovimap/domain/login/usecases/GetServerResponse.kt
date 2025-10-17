package com.example.inovimap.domain.login.usecases

import com.example.inovimap.domain.server.models.LoginResponse
import com.example.inovimap.repository.LoginRepository

class GetServerResponse(
    private val loginRepository: LoginRepository
) {
    suspend operator fun invoke(email: String, password: String): LoginResponse {
        return loginRepository.getServerResponse(email = email, password = password)
    }
}