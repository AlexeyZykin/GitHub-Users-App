package com.alexisdev.github_users_app.users_api.model

data class UserDetailsDTO(
    val login: String,
    val id: Int,
    val avatar_url: String,
    val name: String?,
    val email: String?,
    val company: String?,
    val followers: Int,
    val following: Int,
    val created_at: String
)
