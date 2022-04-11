package com.example.popularlibrariescourse.ui

/*  Состояния приложения для востановления состояния при пересоздании Presenter  */

sealed class AppState {
    object Success : AppState()
    object Error : AppState()
}