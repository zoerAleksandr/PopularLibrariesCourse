package com.example.popularlibrariescourse.ui.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.popularlibrariescourse.R
import com.example.popularlibrariescourse.app
import com.example.popularlibrariescourse.databinding.ActivityMainBinding
import com.example.popularlibrariescourse.ui.registration.RegistrationActivity
import com.google.android.material.snackbar.BaseTransientBottomBar.LENGTH_LONG
import com.google.android.material.snackbar.Snackbar

class LoginActivity : AppCompatActivity(), LoginContract.View {
    private val binding: ActivityMainBinding by viewBinding()
    private var presenter: LoginPresenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        presenter = initPresenter()
        presenter?.onAttach(this)
        binding.loginButton.setOnClickListener {
            presenter?.onLogin(
                binding.loginEditText.text.toString(),
                binding.passwordEditText.text.toString(),
            )
        }

        binding.registrationButton.setOnClickListener {
            startActivity(Intent(this, RegistrationActivity::class.java))
        }
    }

    private fun initPresenter(): LoginPresenter =
        lastCustomNonConfigurationInstance as? LoginPresenter ?: LoginPresenter(app.loginUseCase)

    override fun onRetainCustomNonConfigurationInstance(): Any? = presenter

    override fun setSuccess() {
        binding.root.setBackgroundColor(resources.getColor(R.color.green_for_background, null))
        Snackbar.make(
            binding.root,
            resources.getString(R.string.text_snackbar_by_success),
            LENGTH_LONG
        ).show()
    }

    override fun setError() {
        binding.root.setBackgroundColor(resources.getColor(R.color.red_for_background, null))
    }

    override fun setErrorLogin(error: LoginError) {
        binding.root.setBackgroundColor(resources.getColor(R.color.red_for_background, null))
        Snackbar.make(
            binding.root,
            error.message,
            LENGTH_LONG
        ).show()
    }

    override fun setProgress(progress: Boolean) {
        if (progress) {
            binding.containerLinearLayout.alpha = 0.3f
            binding.loginButton.isEnabled = false
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.containerLinearLayout.alpha = 1f
            binding.loginButton.isEnabled = true
            binding.progressBar.visibility = View.GONE
        }
    }
}