package com.alexisdev.github_users_app.features.user_list.data

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import androidx.paging.rxjava3.flowable
import com.alexisdev.github_users_app.cache.UsersDao
import com.alexisdev.github_users_app.features.user_list.domain.User
import com.alexisdev.github_users_app.features.user_list.domain.UserListRepository
import io.reactivex.rxjava3.core.Flowable

class UserListRepositoryImpl(
    private val usersDao: UsersDao,
    private val userRemoteMediator: UserRemoteMediator
    ) : UserListRepository {
    @OptIn(ExperimentalPagingApi::class)
    override fun fetchUsers(): Flowable<PagingData<User>> {
        return Pager(
            config = PagingConfig(pageSize = 30),
            initialKey = null,
            remoteMediator = userRemoteMediator
        ){ usersDao.pagingSource() }
            .flowable
            .map { pagingData ->
                pagingData.map { it.toUser() }
            }
    }
}