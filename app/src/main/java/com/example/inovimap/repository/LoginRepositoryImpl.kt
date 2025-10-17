package com.example.inovimap.repository

import com.example.inovimap.datasource.LoginDataSource
import com.example.inovimap.domain.server.models.LoginResponse

class LoginRepositoryImpl(
    private val loginDataSource: LoginDataSource
): LoginRepository {

    override suspend fun validateEmail(email: String): String? {
        return loginDataSource.validateEmail(email)
    }

    override suspend fun getServerResponse(email: String, password: String): LoginResponse {
        return loginDataSource.getServerResponse(email =  email, password =  password)
    }
}