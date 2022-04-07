package com.example.popularlibrariescourse.domain

import com.example.popularlibrariescourse.ui.login.StateVerification

interface LoginUseCase {
    fun login(login: String, password: String, callback: (StateVerification) -> Unit)
}