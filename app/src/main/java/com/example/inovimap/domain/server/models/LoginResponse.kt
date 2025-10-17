package com.example.inovimap.domain.server.models

data class LoginResponse(
    val message: String,
    val user: UserInfo?
)
