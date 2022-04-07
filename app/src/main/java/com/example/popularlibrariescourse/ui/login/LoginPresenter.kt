package com.example.popularlibrariescourse.ui.login

import com.example.popularlibrariescourse.domain.LoginUseCase
import com.example.popularlibrariescourse.ui.AppState

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
                        view?.setErrorLogin(result.error)
                    }
                }
                view?.setProgress(false)
            }
        } else {
            state = AppState.Error
            view?.setErrorLogin(LoginError.EMPTY_FIELD)
        }
    }

    /*  Проверка полей логина и пароля на пустоту  */
    private fun checkedFieldForEmpty(login: String, password: String) =
        (login.isNotBlank() && password.isNotBlank())
}