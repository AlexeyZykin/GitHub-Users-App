package com.alexisdev.github_users_app.users_api.model

data class UserDTO(
    val login: String,
    val id: Int,
    val avatar_url: String
)
