package com.alexisdev.github_users_app.features.user_list.presentation

import com.alexisdev.github_users_app.features.user_list.domain.User

fun User.toUserUi() = UserUi(login, id, avatarUrl)