package com.example.inovimap.datasource.converters

import com.example.inovimap.domain.server.models.LoginResponse
import com.example.inovimap.domain.server.models.UserInfo
import com.example.inovimap.remote.dtos.LoginResponseDto
import com.example.inovimap.remote.dtos.UserInfoDto

fun UserInfoDto.toUserInfo(): UserInfo {
    return UserInfo(
        email = email,
        latitude = latitude,
        longitude = longitude
    )
}

fun LoginResponseDto.toLoginResponse(): LoginResponse {
    return LoginResponse(
        message = message,
        user = user?.toUserInfo()
    )
}
