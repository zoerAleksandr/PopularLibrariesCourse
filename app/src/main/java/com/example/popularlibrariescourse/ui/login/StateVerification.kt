package com.example.popularlibrariescourse.ui.login

/*   Состояния результата проверки введенного логина и пароля на соответствие установленным   */

sealed class StateVerification {
    object Success : StateVerification()
    data class ErrorLogin(val error: LoginError) : StateVerification()
}