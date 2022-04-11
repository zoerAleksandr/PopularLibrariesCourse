package com.example.popularlibrariescourse.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.popularlibrariescourse.domain.LoginUseCase

class ViewModelFactory(private val userCase: LoginUseCase): ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return LoginViewModel (userCase) as T
    }
}