package com.example.inovimap.datasource.converters

import com.example.inovimap.domain.server.models.LoginRequest
import com.example.inovimap.domain.server.models.LoginResponse
import com.example.inovimap.domain.server.models.UserInfo
import com.example.inovimap.remote.dtos.LoginRequestDto
import com.example.inovimap.remote.dtos.LoginResponseDto
import com.example.inovimap.remote.dtos.UserInfoDto

fun UserInfo.toDto(): UserInfoDto {
    return UserInfoDto(
        email = email
    )
}

fun UserInfoDto.toUserInfo(): UserInfo {
    return UserInfo(
        email = email
    )
}

fun LoginResponse.toDto(): LoginResponseDto {
    return LoginResponseDto(
        message = message,
        user = user?.toDto()
    )
}

fun LoginResponseDto.toLoginResponse(): LoginResponse {
    return LoginResponse(
        message = message,
        user = user?.toUserInfo()
    )
}

fun LoginRequest.toDto(): LoginRequestDto {
    return LoginRequestDto(
        email = email,
        password = password
    )
}

fun LoginRequestDto.toLoginRequest(): LoginRequest {
    return LoginRequest(
        email = email,
        password = password
    )
}
