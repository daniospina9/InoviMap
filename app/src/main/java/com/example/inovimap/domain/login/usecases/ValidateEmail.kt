package com.example.inovimap.domain.login.usecases

import com.example.inovimap.repository.LoginRepository

class ValidateEmail(
    private val loginRepository: LoginRepository
) {
    suspend operator fun invoke(email: String): String? {
        return loginRepository.validateEmail(email)
    }
}