package com.example.popularlibrariescourse.domain

import com.example.popularlibrariescourse.ui.login.StateVerification
import com.example.popularlibrariescourse.ui.registration.StateRegistration

interface LoginUseCase {
    fun login(login: String, password: String, callback: (StateVerification) -> Unit)
    fun addUser(login: String, password: String, callback: (StateRegistration) -> Unit)
}