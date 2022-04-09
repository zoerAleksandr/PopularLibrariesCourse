package com.example.popularlibrariescourse.ui.registration

import com.example.popularlibrariescourse.utils.Publisher

interface RegistrationViewModelContract {
    val stateRegistration: Publisher<StateRegistration>
    val showText: Publisher<String>

    fun onRegistration(login: String, password: String, passwordConfirmation: String)
}