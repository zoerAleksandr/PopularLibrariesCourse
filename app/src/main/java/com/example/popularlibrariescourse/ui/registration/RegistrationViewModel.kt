package com.example.popularlibrariescourse.ui.registration

import com.example.popularlibrariescourse.utils.Publisher

interface RegistrationViewModel {
    var isSuccess: Publisher<Boolean>
    var isError: Publisher<Boolean>
    var errorRegistration: Publisher<RegistrationError>
    var isProgress: Publisher<Boolean>

    fun onAttach(v: RegistrationContract.View)
    fun onRegistration(login: String, password: String, passwordConfirmation: String)
}