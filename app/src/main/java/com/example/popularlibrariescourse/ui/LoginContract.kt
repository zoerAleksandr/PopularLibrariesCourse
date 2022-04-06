package com.example.popularlibrariescourse.ui

import android.os.Handler

class LoginContract {

    interface View {
        fun setSuccess()
        fun setError()
        fun setErrorEmptyField()
        fun setErrorLogin()
        fun setErrorPassword()
        fun setErrorUnknown()
        fun setProgress(progress: Boolean)
        fun getHandler(): Handler
    }

    interface Presenter {
        fun onAttach(v: View)
        fun onLogin(login: String, password: String)
    }
}