package com.alexisdev.github_users_app.features.user_list.domain

data class User(
    val login: String,
    val id: Int,
    val avatarUrl: String
)