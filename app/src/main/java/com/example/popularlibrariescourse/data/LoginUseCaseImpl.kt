package com.example.popularlibrariescourse.data

import android.os.Handler
import com.example.popularlibrariescourse.domain.LoginApi
import com.example.popularlibrariescourse.domain.LoginUseCase
import com.example.popularlibrariescourse.ui.login.StateVerification
import java.lang.Thread.sleep

class LoginUseCaseImpl(
    private val api: LoginApi,
    private val uiHandler: Handler
): LoginUseCase {
    override fun login(login: String, password: String, callback: (StateVerification) -> Unit) {
        Thread{
            val result = api.login(login, password)
            uiHandler.post {
                sleep(1_000)
                callback(result)
            }
        }.start()
    }
}