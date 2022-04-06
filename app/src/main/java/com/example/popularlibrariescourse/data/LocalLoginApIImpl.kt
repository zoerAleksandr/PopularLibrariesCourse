package com.example.popularlibrariescourse.data

import com.example.popularlibrariescourse.domain.LoginApi
import com.example.popularlibrariescourse.repository.LoginRepository
import com.example.popularlibrariescourse.ui.StateVerification

class LocalLoginApIImpl(private val repository: LoginRepository) : LoginApi {

    override fun registration(
        login: String,
        password: String,
        passwordConfirmation: String
    ): Boolean {
        TODO("Not yet implemented")
    }

    override fun login(login: String, password: String): StateVerification {
        val listUser = repository.getAllUserProfile()
        var state: StateVerification = StateVerification.ErrorUnknown
        for (user in listUser) {
            if (user.login == login && user.password == password) {
                state = StateVerification.Success
            } else {
                StateVerification.ErrorUnknown
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