package com.example.popularlibrariescourse.repository

import com.example.popularlibrariescourse.data.UserProfile

interface LoginRepository {
    fun addUser(login: String, password: String, passwordConfirmation: String): Boolean
    fun login(login: String, password: String): UserProfile
    fun recoverPassword(login: String): String
    fun getAllUserProfile(): MutableList<UserProfile>
}