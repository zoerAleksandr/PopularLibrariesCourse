package com.example.popularlibrariescourse

import com.example.popularlibrariescourse.data.Repository
import com.example.popularlibrariescourse.data.VerificationLoginCallback

private const val ERROR_EMPTY = "empty field"

class LoginPresenter(
    private val view: LoginContract.View,
    private val repository: Repository
) : LoginContract.Presenter, VerificationLoginCallback {

    override fun onLogin(login: String, password: String) {
        if (checkedFieldForEmpty(login, password)) {
            requestInServer(login, password)
        } else {
            view.setError(ERROR_EMPTY)
        }
    }

    private fun requestInServer(login: String, password: String) {
        view.setProgress(true)
        repository.onLogin(login, password, this)
    }

    /*  Проверка полей логина и пароля на пустоту  */
    private fun checkedFieldForEmpty(login: String, password: String): Boolean {
        return (login.isNotBlank() && password.isNotBlank())
    }

    override fun success() {
        view.setProgress(false)
        view.setSuccess()
    }

    override fun failure(message: String) {
        view.setProgress(false)
        view.setError(message)
    }
}