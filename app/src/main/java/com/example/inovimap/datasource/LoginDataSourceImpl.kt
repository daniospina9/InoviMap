package com.example.inovimap.datasource

import java.util.regex.Pattern

class LoginDataSourceImpl(): LoginDataSource {

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
}