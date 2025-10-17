package com.example.inovimap.datasource

import com.example.inovimap.datasource.converters.toLoginResponse
import com.example.inovimap.domain.server.models.LoginResponse
import com.example.inovimap.remote.ServerApi
import com.example.inovimap.remote.dtos.ErrorResponseDto
import com.example.inovimap.remote.dtos.LoginRequestDto
import com.google.gson.Gson
import retrofit2.HttpException
import java.util.regex.Pattern

class LoginDataSourceImpl(
    private val api: ServerApi
) : LoginDataSource {

    override suspend fun validateEmail(email: String): String? {
        val EMAIL_PATTERN =
            "[a-zA-Z0-9+._%\\-]{1,256}@[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}(\\.[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25})+"

        fun isValidEmail(email: String) = Pattern.compile(EMAIL_PATTERN).matcher(email).matches()

        if (email.isBlank()) {
            return "El correo no puede estar vacío"
        }

        if (!isValidEmail(email)) {
            return "El correo no es válido"
        }

        return null
    }

    override suspend fun getServerResponse(email: String, password: String): LoginResponse {
        return try {
            api.login(LoginRequestDto(email = email, password = password)).toLoginResponse()
        } catch (e: HttpException) {
            val errorMessage = try {
                val errorBody = e.response()?.errorBody()?.string()
                val gson = Gson()
                val errorResponse = gson.fromJson(errorBody, ErrorResponseDto::class.java)
                errorResponse.error
            } catch (_: Exception) {
                "Error ${e.code()}: ${e.message()}"
            }

            LoginResponse(
                message = errorMessage,
                user = null
            )
        } catch (e: Exception) {
            LoginResponse(
                message = e.message.orEmpty().ifEmpty { "Ocurrió un error desconocido" },
                user = null
            )
        }
    }
}