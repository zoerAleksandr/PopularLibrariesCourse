package com.example.popularlibrariescourse.data

import com.example.popularlibrariescourse.domain.LoginApi
import com.example.popularlibrariescourse.ui.StateVerification

private const val LOGIN = "Login"
private const val PASSWORD = "Password"

class LocalLoginApIImpl : LoginApi {
    override fun registration(
        login: String,
        password: String,
        passwordConfirmation: String
    ): Boolean {
        TODO("Not yet implemented")
    }

    override fun login(login: String, password: String): StateVerification {
        /*  Проверка введенных логина и пароля на соответствие установленным   */
        return if (login == LOGIN && password == PASSWORD) StateVerification.Success
        else if (login != LOGIN) StateVerification.ErrorLogin
        else if (password != PASSWORD) StateVerification.ErrorPassword
        else StateVerification.ErrorUnknown
    }

    override fun logout(): Boolean {
        TODO("Not yet implemented")
    }

    override fun recoverPassword(login: String) {
        TODO("Not yet implemented")
    }
}