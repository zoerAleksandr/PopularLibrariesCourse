package com.example.popularlibrariescourse.ui.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.popularlibrariescourse.R
import com.example.popularlibrariescourse.app
import com.example.popularlibrariescourse.databinding.ActivityMainBinding
import com.example.popularlibrariescourse.ui.AppState
import com.example.popularlibrariescourse.ui.registration.RegistrationActivity
import com.google.android.material.snackbar.BaseTransientBottomBar.LENGTH_LONG
import com.google.android.material.snackbar.Snackbar

class LoginActivity : AppCompatActivity() {
    private val binding: ActivityMainBinding by viewBinding()

    private val viewModel: LoginViewModel by viewModels {
        ViewModelFactory(app.loginUseCase)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel.getState().observe(this) { appState ->
            renderState(appState)
        }
        binding.loginButton.setOnClickListener {
            viewModel.onLogin(
                binding.loginEditText.text.toString(),
                binding.passwordEditText.text.toString(),
            )
        }

        binding.registrationButton.setOnClickListener {
            startActivity(Intent(this, RegistrationActivity::class.java))
        }
    }

    private fun showProgress(loading: Boolean) {
        if (loading) {
            binding.containerLinearLayout.alpha = 0.3f
            binding.loginButton.isEnabled = false
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.containerLinearLayout.alpha = 1f
            binding.loginButton.isEnabled = true
            binding.progressBar.visibility = View.GONE
        }
    }

    private fun renderState(appState: AppState) {
        when (appState) {
            is AppState.Loading -> {
                showProgress(true)
            }
            is AppState.Success -> {
                showProgress(false)
                binding.root.setBackgroundColor(
                    resources.getColor(
                        R.color.green_for_background,
                        null
                    )
                )
                appState.message?.let { message ->
                    Snackbar.make(
                        binding.root,
                        message,
                        LENGTH_LONG
                    ).show()
                }

            }
            is AppState.Error -> {
                showProgress(false)
                binding.root.setBackgroundColor(
                    resources.getColor(
                        R.color.red_for_background,
                        null
                    )
                )
                Snackbar.make(
                    binding.root,
                    appState.error,
                    LENGTH_LONG
                ).show()
            }
        }
    }
}