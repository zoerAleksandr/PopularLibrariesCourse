package com.example.popularlibrariescourse.ui.registration

import com.example.popularlibrariescourse.domain.LoginUseCase
import com.example.popularlibrariescourse.utils.Publisher

class RegistrationViewModel(private val loginUseCase: LoginUseCase) :
    RegistrationViewModelContract {
    override val stateRegistration: Publisher<StateRegistration> = Publisher()

    override val showText: Publisher<String> = Publisher(true)

    override fun onRegistration(login: String, password: String, passwordConfirmation: String) {
        if (checkedFieldForEmpty(login, password, passwordConfirmation)) {
            stateRegistration.post(StateRegistration.Error(ErrorType.EMPTY_FIELD))
            showText.post(ErrorType.EMPTY_FIELD.message)
        } else if (checkedPasswordConfirmation(password, passwordConfirmation)) {
            stateRegistration.post(StateRegistration.Error(ErrorType.INCORRECT_PASSWORD_CONFIRMATION))
            showText.post(ErrorType.INCORRECT_PASSWORD_CONFIRMATION.message)
        } else {
            stateRegistration.post(StateRegistration.Loading(true))
            loginUseCase.addUser(login, password) { state ->
                when (state) {
                    StateRegistration.Success() -> {
                        stateRegistration.post(StateRegistration.Success())
                        showText.post(StateRegistration.Success().message)
                    }
                    StateRegistration.Error(ErrorType.LOGIN_TAKEN) -> {
                        stateRegistration.post(StateRegistration.Error(ErrorType.LOGIN_TAKEN))
                        showText.post(ErrorType.LOGIN_TAKEN.message)
                    }
                    StateRegistration.Error(ErrorType.UNKNOWN) -> {
                        stateRegistration.post(StateRegistration.Error(ErrorType.UNKNOWN))
                        showText.post(ErrorType.UNKNOWN.message)
                    }
                    else -> {}
                }
                stateRegistration.post(StateRegistration.Loading(false))
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