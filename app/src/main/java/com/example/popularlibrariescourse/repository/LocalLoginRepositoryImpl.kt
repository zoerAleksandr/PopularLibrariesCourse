package com.example.popularlibrariescourse.repository

import com.example.popularlibrariescourse.data.UserProfile

class LocalLoginRepositoryImpl: LoginRepository {
    private val listUserProfile = mutableListOf<UserProfile>()
    init {
        listUserProfile.add(UserProfile("Login", "Password"))
    }

    override fun addUser(
        login: String,
        password: String,
        passwordConfirmation: String
    ): Boolean {
        TODO("Not yet implemented")
    }

    override fun login(login: String, password: String): UserProfile {
        TODO("Not yet implemented")
    }

    override fun recoverPassword(login: String): String {
        TODO("Not yet implemented")
    }

    override fun getAllUserProfile(): MutableList<UserProfile> = listUserProfile
}