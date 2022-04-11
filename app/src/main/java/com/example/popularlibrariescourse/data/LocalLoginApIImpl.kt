package com.example.popularlibrariescourse.data

import com.example.popularlibrariescourse.domain.LoginApi
import com.example.popularlibrariescourse.data.repository.LoginRepository
import com.example.popularlibrariescourse.ui.login.LoginError
import com.example.popularlibrariescourse.ui.login.StateLogin
import com.example.popularlibrariescourse.ui.registration.ErrorType
import com.example.popularlibrariescourse.ui.registration.StateRegistration

class LocalLoginApIImpl(private val repository: LoginRepository) : LoginApi {

    override fun registration(
        login: String,
        password: String
    ): StateRegistration {
        val listUser = repository.getAllUserProfile()
        var loginTaken = true
        var state: StateRegistration =
            StateRegistration.Error(ErrorType.UNKNOWN)
        for (user in listUser) {
            if (user.login == login) {
                state = StateRegistration.Error(ErrorType.LOGIN_TAKEN)
                loginTaken = false
                break
            }
        }

        if (loginTaken) {
            repository.addUser(login, password)
            state = StateRegistration.Success()
        }
        return state
    }

    override fun login(login: String, password: String): StateLogin {
        val listUser = repository.getAllUserProfile()
        var state: StateLogin = StateLogin.ErrorLogin(LoginError.UNKNOWN)
        for (user in listUser) {
            if (user.login == login && user.password == password) {
                state = StateLogin.Success()
            } else if (user.login != login) {
                state = StateLogin.ErrorLogin(LoginError.INCORRECT_LOGIN)
            } else if (user.login == login && user.password != password) {
                state = StateLogin.ErrorLogin(LoginError.INCORRECT_PASSWORD)
            }
        }
        return state
    }

    override fun logout(): Boolean {
        TODO("Not yet implemented")
    }

    override fun recoverPassword(login: String) {
        TODO("Not yet implemented")
    }
}