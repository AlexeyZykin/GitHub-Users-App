package com.alexisdev.github_users_app.features.user_details.presentation

import com.alexisdev.github_users_app.features.user_details.domain.UserDetails

fun UserDetails.toUserDetailsUi() = UserDetailsUi(
    login = login,
    id = id,
    avatarUrl = avatarUrl,
    name = name,
    email = email,
    company = company,
    followers = followers,
    following = following,
    createdAt = createdAt
)