package com.example.core.either

sealed class NetworkError {

    class Api(val error: String) : NetworkError()

    class AuthApi(val errorResponse: AuthenticationError) : NetworkError()

    object Timeout : NetworkError()
}