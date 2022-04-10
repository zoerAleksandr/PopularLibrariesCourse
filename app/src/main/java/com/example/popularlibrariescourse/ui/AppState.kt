package com.example.popularlibrariescourse.ui

/*  Состояния приложения для востановления состояния при пересоздании Presenter  */

sealed class AppState {
    data class Success(val message: String? = "Вход выполнен") : AppState()
    data class Error(val error: String) : AppState()
    object Loading : AppState()
}