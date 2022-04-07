package com.example.popularlibrariescourse.repository

import com.example.popularlibrariescourse.data.UserProfile

interface LoginRepository {
    fun addUser(login: String, password: String): Boolean
    fun getAllUserProfile(): MutableList<UserProfile>
}