package com.example.popularlibrariescourse.ui.login

import com.example.popularlibrariescourse.App
import com.example.popularlibrariescourse.R

sealed class StateLogin {
    data class Success(val message: String = App.instance.getString(R.string.text_snackbar_by_success_login)) : StateLogin()
    data class ErrorLogin(val error: LoginError) : StateLogin()
}

enum class LoginError(val message: String) {
    EMPTY_FIELD(App.instance.getString(R.string.error_text_empty_fields)),
    INCORRECT_LOGIN(App.instance.getString(R.string.error_text_login)),
    INCORRECT_PASSWORD(App.instance.getString(R.string.error_text_password)),
    UNKNOWN(App.instance.getString(R.string.error_text_unknown))
}