package com.example.popularlibrariescourse.ui.registration

import com.example.popularlibrariescourse.domain.LoginUseCase
import com.example.popularlibrariescourse.utils.Publisher

class RegistrationViewModel(private val loginUseCase: LoginUseCase) :
    RegistrationViewModelContract {
    override val isSuccess: Publisher<Boolean> = Publisher()
    override val isError: Publisher<Boolean> = Publisher()
    override val errorRegistration: Publisher<RegistrationError> = Publisher()
    override val isProgress: Publisher<Boolean> = Publisher()

    override fun onRegistration(login: String, password: String, passwordConfirmation: String) {
        if (checkedFieldForEmpty(login, password, passwordConfirmation)) {
            errorRegistration.post(RegistrationError.EMPTY_FIELD)
        } else if (checkedPasswordConfirmation(password, passwordConfirmation)) {
            errorRegistration.post(RegistrationError.INCORRECT_PASSWORD_CONFIRMATION)
        } else {
            isProgress.post(true)
            loginUseCase.addUser(login, password) { state ->
                when (state) {
                    is StateRegistration.Success -> {
                        isSuccess.post(true)
                    }
                    is StateRegistration.ErrorRegistration -> {
                        errorRegistration.post(state.error)
                    }
                }
                isProgress.post(false)
            }
        }
    }

    private fun checkedFieldForEmpty(
        login: String,
        password: String,
        passwordConfirmation: String
    ): Boolean {
        return (login.isBlank() || password.isBlank() || passwordConfirmation.isBlank())
    }

    private fun checkedPasswordConfirmation(password: String, passwordConfirmation: String) =
        password != passwordConfirmation
}