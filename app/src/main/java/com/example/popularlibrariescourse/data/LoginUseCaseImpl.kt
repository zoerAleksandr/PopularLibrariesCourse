package com.example.popularlibrariescourse.data

import android.os.Handler
import com.example.popularlibrariescourse.domain.LoginApi
import com.example.popularlibrariescourse.domain.LoginUseCase
import com.example.popularlibrariescourse.ui.login.StateLogin
import com.example.popularlibrariescourse.ui.registration.StateRegistration
import java.lang.Thread.sleep

class LoginUseCaseImpl(
    private val api: LoginApi,
    private val uiHandler: Handler
) : LoginUseCase {
    override fun login(login: String, password: String, callback: (StateLogin) -> Unit) {
        Thread {
            val result = api.login(login, password)
            uiHandler.post {
                sleep(1_000)
                callback(result)
            }
        }.start()
    }

    override fun addUser(
        login: String,
        password: String,
        callback: (StateRegistration) -> Unit
    ) {
        Thread {
            val result = api.registration(login, password)
            sleep(2_000)
            callback(result)
        }.start()
    }
}