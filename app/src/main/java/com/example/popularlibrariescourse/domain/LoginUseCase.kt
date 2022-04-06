package com.example.popularlibrariescourse.domain

import com.example.popularlibrariescourse.ui.StateVerification

interface LoginUseCase {
    fun login(login: String, password: String, callback: (StateVerification) -> Unit)
}