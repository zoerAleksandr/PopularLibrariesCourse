package com.example.popularlibrariescourse.domain

import com.example.popularlibrariescourse.ui.StateVerification

interface LoginApi {
    fun registration(login: String, password: String, passwordConfirmation: String): Boolean
    fun login(login: String, password: String): StateVerification
    fun logout() : Boolean
    fun recoverPassword(login: String)
}