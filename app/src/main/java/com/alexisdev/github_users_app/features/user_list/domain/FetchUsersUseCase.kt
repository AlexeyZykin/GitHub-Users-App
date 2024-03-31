package com.alexisdev.github_users_app.features.user_list.domain

import androidx.paging.PagingData
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single

class FetchUsersUseCase(private val userListRepository: UserListRepository) {
    fun invoke(): Flowable<PagingData<User>> {
        return userListRepository.fetchUsers()
    }
}