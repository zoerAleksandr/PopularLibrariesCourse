package com.example.popularlibrariescourse.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.popularlibrariescourse.domain.LoginUseCase
import com.example.popularlibrariescourse.ui.AppState

class LoginViewModel(
    private val useCase: LoginUseCase,
    private val liveData: MutableLiveData<AppState> = MutableLiveData()
) : ViewModel() {

    fun getState(): LiveData<AppState> = liveData

    fun onLogin(login: String, password: String) {
        if (checkedFieldForEmpty(login, password)) {
            liveData.value = AppState.Loading
            useCase.login(login, password) { stateLogin ->
                when (stateLogin) {
                    is StateLogin.Success -> {
                        liveData.value = AppState.Success()
                    }
                    StateLogin.ErrorLogin(LoginError.INCORRECT_LOGIN) -> {
                        liveData.value = AppState.Error(LoginError.INCORRECT_LOGIN.message)
                    }
                    StateLogin.ErrorLogin(LoginError.INCORRECT_PASSWORD) -> {
                        liveData.value = AppState.Error(LoginError.INCORRECT_PASSWORD.message)
                    }
                    StateLogin.ErrorLogin(LoginError.UNKNOWN) -> {
                        liveData.value = AppState.Error(LoginError.UNKNOWN.message)
                    }
                    else -> {}
                }
            }
        } else {
            liveData.value = AppState.Error(LoginError.EMPTY_FIELD.message)
        }
    }

    private fun checkedFieldForEmpty(login: String, password: String) =
        (login.isNotBlank() && password.isNotBlank())
}