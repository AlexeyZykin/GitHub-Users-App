package com.alexisdev.github_users_app.features.user_list.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.map
import androidx.paging.rxjava3.cachedIn
import com.alexisdev.github_users_app.features.user_list.domain.FetchUsersUseCase
import io.reactivex.rxjava3.disposables.CompositeDisposable
import kotlinx.coroutines.ExperimentalCoroutinesApi


class UserListViewModel(private val fetchUsersUseCase: FetchUsersUseCase) : ViewModel() {
    private val compositeDisposable: CompositeDisposable by lazy { CompositeDisposable() }
    private val _users = MutableLiveData<PagingData<UserUi>>()
    val users: LiveData<PagingData<UserUi>> get() = _users

    @OptIn(ExperimentalCoroutinesApi::class)
    fun fetchUsers() {
            fetchUsersUseCase.invoke()
                .cachedIn(viewModelScope)
                .subscribe { pagingData ->
                    _users.postValue(
                        pagingData.map { it.toUserUi() }
                    )
                }
                .let { compositeDisposable.add(it) }



    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}