package com.example.popularlibrariescourse.ui.registration

sealed class StateRegistration {
    object Success: StateRegistration()
    data class ErrorRegistration(val error: RegistrationError): StateRegistration()
}