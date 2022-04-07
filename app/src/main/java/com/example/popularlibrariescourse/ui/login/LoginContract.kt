package com.example.popularlibrariescourse.ui.login

import android.os.Handler

class LoginContract {

    interface View {
        fun setSuccess()
        fun setError()
        fun setErrorLogin(error: LoginError)
        fun setProgress(progress: Boolean)
    }

    interface Presenter {
        fun onAttach(v: View)
        fun onLogin(login: String, password: String)
    }
}