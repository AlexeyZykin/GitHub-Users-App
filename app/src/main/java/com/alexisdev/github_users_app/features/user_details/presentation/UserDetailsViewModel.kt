package com.alexisdev.github_users_app.features.user_details.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.alexisdev.github_users_app.features.user_details.domain.FetchUserDetailsUseCase
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers


class UserDetailsViewModel(
    private val fetchUserDetailsUseCase: FetchUserDetailsUseCase
) : ViewModel() {
    private val compositeDisposable: CompositeDisposable by lazy { CompositeDisposable() }
    private val _userDetails = MutableLiveData<UserDetailsUiState<UserDetailsUi>>()
    val userDetails: LiveData<UserDetailsUiState<UserDetailsUi>> get() = _userDetails


    fun fetchUserDetails(username: String) {
        compositeDisposable.add(
            fetchUserDetailsUseCase.invoke(username)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {
                        _userDetails.postValue(UserDetailsUiState.Success(it.toUserDetailsUi()))
                    },
                    {
                        _userDetails.postValue(UserDetailsUiState.Error(it.message))
                    }
                )
        )
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}