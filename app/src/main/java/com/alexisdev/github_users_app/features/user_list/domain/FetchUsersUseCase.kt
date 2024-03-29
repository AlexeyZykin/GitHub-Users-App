package com.alexisdev.github_users_app.features.user_list.domain

import io.reactivex.rxjava3.core.Observable

class FetchUsersUseCase(private val userListRepository: UserListRepository) {
    fun invoke(): Observable<List<User>> {
        return userListRepository.fetchUsers()
    }
}