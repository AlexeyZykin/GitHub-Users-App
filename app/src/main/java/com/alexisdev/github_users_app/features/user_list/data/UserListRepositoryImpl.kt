package com.alexisdev.github_users_app.features.user_list.data

import com.alexisdev.github_users_app.features.user_list.domain.User
import com.alexisdev.github_users_app.features.user_list.domain.UserListRepository
import com.alexisdev.github_users_app.users_api.GitHubUsersApi
import io.reactivex.rxjava3.core.Observable

class UserListRepositoryImpl(private val gitHubUsersApi: GitHubUsersApi) : UserListRepository {
    override fun fetchUsers(): Observable<List<User>> {
        return gitHubUsersApi.fetchUsers().map { it.map { it.toUser() }  }
    }
}