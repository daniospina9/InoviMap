package com.example.inovimap.datasource

interface LoginDataSource {
    suspend fun validateEmail(email: String): String?
}