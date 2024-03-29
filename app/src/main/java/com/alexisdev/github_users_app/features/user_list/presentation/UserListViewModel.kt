package com.alexisdev.github_users_app.features.user_list.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.alexisdev.github_users_app.features.user_list.domain.FetchUsersUseCase
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers


class UserListViewModel(private val fetchUsersUseCase: FetchUsersUseCase) : ViewModel() {
    private val compositeDisposable: CompositeDisposable by lazy { CompositeDisposable() }
    private val _users = MutableLiveData<UserListUiState<List<UserUi>>>()
    val users: LiveData<UserListUiState<List<UserUi>>> get() = _users

    init {
        fetchUsers()
    }

    fun fetchUsers() {
        compositeDisposable.add(
            fetchUsersUseCase.invoke()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    _users.postValue(UserListUiState.Success(
                        it.map { it.toUserUi() }
                    ))
                },
                    {
                        _users.postValue(UserListUiState.Error(it.message))
                    }
                )
        )

    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}