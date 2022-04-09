package com.example.popularlibrariescourse.domain

import com.example.popularlibrariescourse.ui.login.StateVerification
import com.example.popularlibrariescourse.ui.registration.StateRegistration

interface LoginApi {
    fun registration(login: String, password: String): StateRegistration
    fun login(login: String, password: String): StateVerification
    fun logout(): Boolean
    fun recoverPassword(login: String)
}