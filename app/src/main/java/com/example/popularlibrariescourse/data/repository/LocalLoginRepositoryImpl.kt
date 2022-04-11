package com.example.popularlibrariescourse.data.repository

import android.util.Log
import com.example.popularlibrariescourse.domain.UserProfile

class LocalLoginRepositoryImpl : LoginRepository {
    private val listUserProfile = mutableListOf<UserProfile>()

    init {
        listUserProfile.add(UserProfile("Login", "Password"))
    }

    override fun addUser(
        login: String,
        password: String,
    ): Boolean {
        for (user in listUserProfile) {
            if (user.login == login) {
                Log.d("Debug", "${user.login} == $login")
                return false
            }
        }
        listUserProfile.add(UserProfile(login, password))
        return true
    }

    override fun getAllUserProfile(): MutableList<UserProfile> = listUserProfile
}