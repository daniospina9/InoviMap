package com.example.inovimap.repository

interface LoginRepository {

    suspend fun validateEmail(email: String): String?
}