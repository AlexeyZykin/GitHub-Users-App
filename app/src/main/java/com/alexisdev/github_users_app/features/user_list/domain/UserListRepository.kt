package com.alexisdev.github_users_app.features.user_list.domain

import androidx.paging.PagingData
import com.alexisdev.github_users_app.features.user_list.domain.User
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single

interface UserListRepository {
    fun fetchUsers(): Flowable<PagingData<User>>
}