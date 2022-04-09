package com.example.popularlibrariescourse.ui.registration

import com.example.popularlibrariescourse.utils.Publisher

interface RegistrationViewModelContract {
    val isSuccess: Publisher<Boolean>
    val isError: Publisher<Boolean>
    val errorRegistration: Publisher<RegistrationError>
    val isProgress: Publisher<Boolean>

    fun onRegistration(login: String, password: String, passwordConfirmation: String)
}