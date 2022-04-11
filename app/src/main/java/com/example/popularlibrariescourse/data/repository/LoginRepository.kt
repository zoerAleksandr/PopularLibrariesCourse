package com.example.popularlibrariescourse.data.repository

import com.example.popularlibrariescourse.domain.UserProfile

interface LoginRepository {
    fun addUser(login: String, password: String): Boolean
    fun getAllUserProfile(): MutableList<UserProfile>
}