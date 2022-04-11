package com.example.popularlibrariescourse

import android.app.Application
import android.content.Context
import android.os.Handler
import android.os.Looper
import com.example.popularlibrariescourse.data.LocalLoginApIImpl
import com.example.popularlibrariescourse.data.LoginUseCaseImpl
import com.example.popularlibrariescourse.data.repository.LocalLoginRepositoryImpl
import com.example.popularlibrariescourse.data.repository.LoginRepository
import com.example.popularlibrariescourse.domain.LoginApi
import com.example.popularlibrariescourse.domain.LoginUseCase

class App : Application() {
    companion object {
        lateinit var instance: App
            private set
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
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
