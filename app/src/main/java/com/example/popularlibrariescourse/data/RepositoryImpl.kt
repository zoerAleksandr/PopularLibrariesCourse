package com.example.popularlibrariescourse.data

private const val LOGIN = "Login"
private const val PASSWORD = "Password"

private const val ERROR_PASSWORD = "incorrect password"
private const val ERROR_LOGIN = "incorrect login"

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