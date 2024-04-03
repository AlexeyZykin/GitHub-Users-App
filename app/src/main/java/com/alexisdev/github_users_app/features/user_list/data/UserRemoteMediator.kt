package com.alexisdev.github_users_app.features.user_list.data

import android.net.http.HttpException
import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.paging.rxjava3.RxRemoteMediator
import androidx.room.withTransaction
import com.alexisdev.github_users_app.cache.UserDBO
import com.alexisdev.github_users_app.cache.UsersDao
import com.alexisdev.github_users_app.cache.UsersDataBase
import com.alexisdev.github_users_app.features.user_list.domain.User
import com.alexisdev.github_users_app.users_api.GitHubUsersApi
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.core.SingleObserver
import io.reactivex.rxjava3.schedulers.Schedulers
import java.io.IOException

@OptIn(ExperimentalPagingApi::class)
class UserRemoteMediator(
    private val usersDataBase: UsersDataBase,
    private val gitHubUsersApi: GitHubUsersApi
): RxRemoteMediator<Int, UserDBO>() {


    override fun loadSingle(
        loadType: LoadType,
        state: PagingState<Int, UserDBO>
    ): Single<MediatorResult> {
        val loadKey = when (loadType) {
            LoadType.REFRESH -> 0
            LoadType.PREPEND -> {
                return Single.just(MediatorResult.Success(true))
            }
            LoadType.APPEND -> {
                val lastItem = state.lastItemOrNull()
                if (lastItem == null) {
                    return Single.just(MediatorResult.Success(true))
                }
                lastItem.id
            }
        }

        return gitHubUsersApi.fetchUsers(loadKey, state.config.pageSize)
            .subscribeOn(Schedulers.io())
            .map { response ->
                usersDataBase.runInTransaction {
                    if (loadType == LoadType.REFRESH) {
                        usersDataBase.getUsersDao().clearUsers()
                    }
                    val usersDbo = response.map { it.toUserDBO() }
                    usersDataBase.getUsersDao().insertAll(usersDbo)
                }
                MediatorResult.Success(endOfPaginationReached = response.isEmpty()) as MediatorResult
            }
            .onErrorResumeNext { e ->
                if (e is Exception) {
                    Single.just(MediatorResult.Error(e))
                } else {
                    Single.error(e)
                }
            }
    }

}