package com.example.popularlibrariescourse

import java.lang.Thread.sleep

private const val LOGIN = "Login"
private const val PASSWORD = "Password"

class LoginPresenter : LoginContract.Presenter {
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
            Thread {
                sleep(1500)
                view?.getHandler()?.post {
                    when (dataVerification(login, password)) {
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
                    view?.setProgress(false)
                }
            }.start()
        } else {
            state = AppState.Error
            view?.setErrorEmptyField()
        }
    }

    /*  Проверка введенных логина и пароля на соответствие установленным   */
    private fun dataVerification(login: String, password: String): StateVerification {
        return if (login == LOGIN && password == PASSWORD) StateVerification.Success
        else if (login != LOGIN) StateVerification.ErrorLogin
        else if (password != PASSWORD) StateVerification.ErrorPassword
        else StateVerification.ErrorUnknown
    }

    /*  Проверка полей логина и пароля на пустоту  */
    private fun checkedFieldForEmpty(login: String, password: String) =
        (login.isNotBlank() && password.isNotBlank())
}