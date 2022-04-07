package com.example.popularlibrariescourse.ui.registration

enum class RegistrationError(val message: String) {
    EMPTY_FIELD("Заполните все поля"),
    LOGIN_TAKEN("Логин занят"),
    INCORRECT_PASSWORD_CONFIRMATION("Неверное подтверждение пароля"),
    UNKNOWN("Неизвестная ошибка")

}