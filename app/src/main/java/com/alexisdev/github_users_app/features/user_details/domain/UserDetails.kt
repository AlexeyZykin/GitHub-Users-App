package com.alexisdev.github_users_app.features.user_details.domain

data class UserDetails(
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