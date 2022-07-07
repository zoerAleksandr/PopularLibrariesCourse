package com.example.popularlibrariescourse.data

interface VerificationLoginCallback {
    fun success()
    fun failure(message: String)
}