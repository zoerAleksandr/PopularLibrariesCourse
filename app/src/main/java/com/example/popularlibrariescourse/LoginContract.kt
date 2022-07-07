package com.example.popularlibrariescourse

class LoginContract {

    interface View {
        fun setSuccess()
        fun setError(message: String)
        fun setProgress(progress: Boolean)
    }

    interface Presenter {
        fun onLogin(login: String, password: String)
    }
}