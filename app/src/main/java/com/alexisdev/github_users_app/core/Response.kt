package com.alexisdev.github_users_app.core

sealed class Response<T>(val data: T? = null, val error: String? = null) {
    class Success<T>(data: T): Response<T>(data)
    class Error<T>(message: String?, data: T? = null): Response<T>(data, message)
    class Loading<T>: Response<T>()
}