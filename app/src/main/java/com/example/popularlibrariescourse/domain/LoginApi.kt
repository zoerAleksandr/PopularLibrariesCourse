package com.example.popularlibrariescourse.domain

import com.example.popularlibrariescourse.ui.login.StateLogin
import com.example.popularlibrariescourse.ui.registration.StateRegistration

interface LoginApi {
    fun registration(login: String, password: String): StateRegistration
    fun login(login: String, password: String): StateLogin
    fun logout(): Boolean
    fun recoverPassword(login: String)
}