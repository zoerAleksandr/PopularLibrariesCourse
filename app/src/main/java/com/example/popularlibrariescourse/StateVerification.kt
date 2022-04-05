package com.example.popularlibrariescourse

/*   Состояния результата проверки введенного логина и пароля на соответствие установленным   */

sealed class StateVerification {
    object Success : StateVerification()
    object ErrorLogin : StateVerification()
    object ErrorPassword : StateVerification()
    object ErrorUnknown : StateVerification()
}