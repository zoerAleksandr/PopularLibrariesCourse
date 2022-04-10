package com.example.popularlibrariescourse.ui.login

/*   Состояния результата проверки введенного логина и пароля на соответствие установленным   */

sealed class StateLogin {
    object Success : StateLogin()
    data class ErrorLogin(val error: LoginError) : StateLogin()
}

enum class LoginError(val message: String) {
    EMPTY_FIELD("Заполните все поля"),
    INCORRECT_LOGIN("Данный логин не зарегистрирован"),
    INCORRECT_PASSWORD("Неверный пароль"),
    UNKNOWN("Неизвестная ошибка")
}