package com.example.inovimap.repository

import com.example.inovimap.domain.server.models.LoginResponse

interface LoginRepository {

    suspend fun validateEmail(email: String): String?

    suspend fun getServerResponse(email: String, password: String): LoginResponse
}