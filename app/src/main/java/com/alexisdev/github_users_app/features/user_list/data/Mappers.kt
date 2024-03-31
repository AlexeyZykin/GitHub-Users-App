package com.alexisdev.github_users_app.features.user_list.data

import com.alexisdev.github_users_app.cache.UserDBO
import com.alexisdev.github_users_app.features.user_list.domain.User
import com.alexisdev.github_users_app.users_api.model.UserDTO


fun UserDTO.toUser() = User(login, id, avatar_url)

fun UserDTO.toUserDBO() = UserDBO(login, id, avatar_url)

fun UserDBO.toUser() = User(login, id, avatarUrl)