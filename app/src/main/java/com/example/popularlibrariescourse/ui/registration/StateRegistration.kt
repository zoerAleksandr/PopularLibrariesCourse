package com.example.popularlibrariescourse.ui.registration

sealed class StateRegistration {
    data class Success(val message: String = "Логин зарегистрирован") : StateRegistration()
    data class Error(val errorType: ErrorType?) : StateRegistration()
    data class Loading(val loading: Boolean?) : StateRegistration()
}

enum class ErrorType(val message: String) {
    EMPTY_FIELD("Заполните все поля"),
    LOGIN_TAKEN("Логин занят"),
    INCORRECT_PASSWORD_CONFIRMATION("Неверное подтверждение пароля"),
    UNKNOWN("Неизвестная ошибка")
}