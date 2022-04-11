package com.example.popularlibrariescourse.ui.login

enum class LoginError(val message: String) {
    EMPTY_FIELD("Заполните все поля"),
    INCORRECT_LOGIN("Данный логин не зарегистрирован"),
    INCORRECT_PASSWORD("Неверный пароль"),
    UNKNOWN("Неизвестная ошибка")
}