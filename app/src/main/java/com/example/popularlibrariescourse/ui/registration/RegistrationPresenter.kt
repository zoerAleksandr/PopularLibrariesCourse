package com.example.popularlibrariescourse.ui.registration

import com.example.popularlibrariescourse.domain.LoginUseCase
import com.example.popularlibrariescourse.ui.AppState

class RegistrationPresenter(private val useCase: LoginUseCase) : RegistrationContract.Presenter {
    private var view: RegistrationContract.View? = null
    private var state: AppState? = null
    override fun onAttach(v: RegistrationContract.View) {
        view = v
        when (state) {
            is AppState.Success -> {
                view?.setSuccess()
            }
            is AppState.Error -> {
                view?.setError()
            }
            else -> {}
        }
    }

    override fun onRegistration(login: String, password: String, passwordConfirmation: String) {
        if (checkedFieldForEmpty(login, password, passwordConfirmation)) {
            state = AppState.Error
            view?.setErrorRegistration(RegistrationError.EMPTY_FIELD)
        } else if (checkedPasswordConfirmation(password, passwordConfirmation)) {
            state = AppState.Error
            view?.setErrorRegistration(RegistrationError.INCORRECT_PASSWORD_CONFIRMATION)
        } else {
            view?.setProgress(true)
            useCase.addUser(login, password) { result ->
                when (result) {
                    is StateRegistration.Success -> {
                        state = AppState.Success
                        view?.setSuccess()
                    }
                    is StateRegistration.ErrorRegistration -> {
                        state = AppState.Error
                        view?.setErrorRegistration(result.error)
                    }
                }
                view?.setProgress(false)
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