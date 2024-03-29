package com.alexisdev.github_users_app.features.user_details.data

import com.alexisdev.github_users_app.features.user_details.domain.UserDetails
import com.alexisdev.github_users_app.users_api.model.UserDetailsDTO

fun UserDetailsDTO.toUserDetails() = UserDetails(
    login = login,
    id = id,
    avatarUrl = avatar_url,
    name = name,
    email = email,
    company = company,
    followers = followers,
    following = following,
    createdAt = created_at
)