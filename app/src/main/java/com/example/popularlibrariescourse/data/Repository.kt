package com.example.popularlibrariescourse.data

interface Repository {
    fun onLogin(login: String, password: String, verification: VerificationLoginCallback)
}