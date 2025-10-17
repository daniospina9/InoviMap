package com.example.inovimap.remote

import com.example.inovimap.remote.dtos.LoginRequestDto
import com.example.inovimap.remote.dtos.LoginResponseDto
import retrofit2.http.Body
import retrofit2.http.POST

interface ServerApi {
    @POST("/login")
    suspend fun login(@Body request: LoginRequestDto): LoginResponseDto
}