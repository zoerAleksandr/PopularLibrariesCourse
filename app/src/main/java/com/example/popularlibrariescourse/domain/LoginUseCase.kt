package com.example.popularlibrariescourse.domain

import com.example.popularlibrariescourse.ui.login.StateLogin
import com.example.popularlibrariescourse.ui.registration.StateRegistration

interface LoginUseCase {
    fun login(login: String, password: String, callback: (StateLogin) -> Unit)
    fun addUser(login: String, password: String, callback: (StateRegistration) -> Unit)
}