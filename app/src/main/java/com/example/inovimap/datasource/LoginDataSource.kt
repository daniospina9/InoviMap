package com.example.inovimap.datasource

import com.example.inovimap.domain.server.models.LoginResponse

interface LoginDataSource {
    suspend fun validateEmail(email: String): String?

    suspend fun getServerResponse(email: String, password: String): LoginResponse
}