package com.alexisdev.github_users_app.features.user_list.domain

import com.alexisdev.github_users_app.features.user_list.domain.User
import io.reactivex.rxjava3.core.Observable

interface UserListRepository {
    fun fetchUsers(): Observable<List<User>>
}