package com.alexisdev.github_users_app.features.user_list.presentation


sealed class UserListUiState<T>(val data: T? = null, val error: String? = null) {
    class Success<T>(data: T?): UserListUiState<T>(data)
    class Error<T>(error: String?): UserListUiState<T>(null, error)
    class Loading<T>: UserListUiState<T>(null, null)
}