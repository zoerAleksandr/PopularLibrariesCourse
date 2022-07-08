package com.example

import com.example.popularlibrariescourse.ERROR_EMPTY
import com.example.popularlibrariescourse.LoginContract
import com.example.popularlibrariescourse.LoginPresenter
import com.example.popularlibrariescourse.data.*
import com.nhaarman.mockito_kotlin.inOrder
import com.nhaarman.mockito_kotlin.times
import com.nhaarman.mockito_kotlin.verify
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class LoginPresenterTest {

    private lateinit var presenter: LoginPresenter

    @Mock
    private lateinit var view: LoginContract.View

    @Mock
    private lateinit var repository: Repository

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        presenter = LoginPresenter(view, repository)
    }

    @Test
    fun repositoryTest_onLogin() {
        presenter.onLogin(LOGIN, PASSWORD)
        verify(repository).onLogin(LOGIN, PASSWORD, presenter)
    }

    @Test
    fun viewTest_setProgress() {
        presenter.onLogin("any", "any")
        verify(view, times(1)).setProgress(true)
    }

    @Test
    fun viewTest_setErrorLogin() {
        presenter.failure(ERROR_LOGIN)
        view.let {
            verify(it).setProgress(false)
            verify(it).setError(ERROR_LOGIN)
        }
    }

    @Test
    fun viewTest_setErrorPassword() {
        presenter.failure(ERROR_PASSWORD)
        view.let {
            verify(it).setProgress(false)
            verify(view).setError(ERROR_PASSWORD)
        }
    }

    @Test
    fun viewTest_setErrorEmpty() {
        presenter.onLogin("", "")
        verify(view).setError(ERROR_EMPTY)
    }

    @Test
    fun viewTest_setErrorSpace() {
        presenter.onLogin(" ", " ")
        verify(view).setError(ERROR_EMPTY)
    }

    @Test
    fun viewTest_setSuccess() {
        val inOrder = inOrder(view)
        presenter.success()
        inOrder.verify(view).setProgress(false)
        inOrder.verify(view).setSuccess()
    }
}