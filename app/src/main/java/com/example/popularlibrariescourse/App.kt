package com.example.popularlibrariescourse

import android.app.Application
import android.content.Context
import android.os.Handler
import android.os.Looper
import com.example.popularlibrariescourse.data.LocalLoginApIImpl
import com.example.popularlibrariescourse.data.LoginUseCaseImpl
import com.example.popularlibrariescourse.domain.LoginApi
import com.example.popularlibrariescourse.domain.LoginUseCase
import com.example.popularlibrariescourse.repository.LocalLoginRepositoryImpl
import com.example.popularlibrariescourse.repository.LoginRepository

class App : Application() {
    private val repository: LoginRepository by lazy { LocalLoginRepositoryImpl() }
    private val loginApi: LoginApi by lazy { LocalLoginApIImpl(repository) }
    val loginUseCase: LoginUseCase by lazy {
        LoginUseCaseImpl(
            loginApi,
            Handler(Looper.getMainLooper())
        )
    }
}

val Context.app: App
    get() {
        return applicationContext as App
    }
