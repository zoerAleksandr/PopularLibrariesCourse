package com.example.popularlibrariescourse.ui

import com.example.popularlibrariescourse.domain.LoginUseCase

class LoginPresenter(private val loginUseCase: LoginUseCase) : LoginContract.Presenter {
    private var view: LoginContract.View? = null
    private var state: AppState? = null

    override fun onAttach(v: LoginContract.View) {
        view = v
        when (state) {
            is AppState.Success -> {
                view?.setSuccess()
            }
            is AppState.Error -> {
                view?.setError()
            }
            else -> {}
        }
    }

    override fun onLogin(login: String, password: String) {
        if (checkedFieldForEmpty(login, password)) {
            view?.setProgress(true)
            loginUseCase.login(login, password) { result ->
                when (result) {
                    is StateVerification.Success -> {
                        state = AppState.Success
                        view?.setSuccess()
                    }
                    is StateVerification.ErrorLogin -> {
                        state = AppState.Error
                        view?.setErrorLogin()
                    }
                    is StateVerification.ErrorPassword -> {
                        state = AppState.Error
                        view?.setErrorPassword()
                    }
                    is StateVerification.ErrorUnknown -> {
                        state = AppState.Error
                        view?.setErrorUnknown()
                    }
                }
            }
            view?.setProgress(false)
        } else {
            state = AppState.Error
            view?.setErrorEmptyField()
        }
    }

    /*  Проверка полей логина и пароля на пустоту  */
    private fun checkedFieldForEmpty(login: String, password: String) =
        (login.isNotBlank() && password.isNotBlank())
}