package com.example.popularlibrariescourse.ui.registration

import com.example.popularlibrariescourse.App
import com.example.popularlibrariescourse.R

sealed class StateRegistration {
    data class Success(val message: String = App.instance.getString(R.string.text_snackbar_by_success_registration)) :
        StateRegistration()

    data class Error(val errorType: ErrorType?) : StateRegistration()
}

enum class ErrorType(val message: String) {
    EMPTY_FIELD(App.instance.getString(R.string.error_text_empty_fields)),
    LOGIN_TAKEN(App.instance.getString(R.string.error_text_login_taken)),
    INCORRECT_PASSWORD_CONFIRMATION(App.instance.getString(R.string.error_text_incorrect_password_confirmation)),
    UNKNOWN(App.instance.getString(R.string.error_text_unknown))
}