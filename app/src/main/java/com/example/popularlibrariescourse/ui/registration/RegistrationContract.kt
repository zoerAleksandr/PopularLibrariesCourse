package com.example.popularlibrariescourse.ui.registration

class RegistrationContract {
    interface View {
        fun setSuccess()
        fun setError()
        fun setErrorRegistration(state: StateRegistration)
        fun setProgress(progress: Boolean)
    }

    interface Presenter {
        fun onAttach(v: View)
        fun onRegistration(login: String, password: String, passwordConfirmation: String)
    }
}