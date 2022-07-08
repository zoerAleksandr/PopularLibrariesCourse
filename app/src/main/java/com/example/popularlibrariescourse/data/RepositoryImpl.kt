package com.example.popularlibrariescourse.data

const val LOGIN = "Login"
const val PASSWORD = "Password"

const val ERROR_PASSWORD = "incorrect password"
const val ERROR_LOGIN = "incorrect login"

class RepositoryImpl : Repository {

    override fun onLogin(login: String, password: String, verification: VerificationLoginCallback) {
        if (login == LOGIN && password == PASSWORD) {
            verification.success()
        } else if (login != LOGIN) {
            verification.failure(ERROR_LOGIN)
        } else if (password != PASSWORD) {
            verification.failure(ERROR_PASSWORD)
        }
    }
}