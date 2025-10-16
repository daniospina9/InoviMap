package com.example.inovimap.repository

import com.example.inovimap.datasource.LoginDataSource

class LoginRepositoryImpl(
    private val loginDataSource: LoginDataSource
): LoginRepository {

    override suspend fun validateEmail(email: String): String? {
        return loginDataSource.validateEmail(email)
    }
}