package com.alexisdev.github_users_app.features.user_details.presentation

sealed class UserDetailsUiState<T>(val data: T? = null, val error: String? = null) {
    class Success<T>(data: T): UserDetailsUiState<T>(data)
    class Error<T>(message: String?, data: T? = null): UserDetailsUiState<T>(data, message)
    class Loading<T>: UserDetailsUiState<T>()
}