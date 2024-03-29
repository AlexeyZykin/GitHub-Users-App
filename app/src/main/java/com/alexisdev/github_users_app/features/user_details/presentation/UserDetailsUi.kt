package com.alexisdev.github_users_app.features.user_details.presentation

data class UserDetailsUi(
    val login: String,
    val id: Int,
    val avatarUrl: String,
    val name: String?,
    val email: String?,
    val company: String?,
    val followers: Int,
    val following: Int,
    val createdAt: String
)
