package com.example.popularlibrariescourse

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.popularlibrariescourse.databinding.ActivityMainBinding
import com.google.android.material.snackbar.BaseTransientBottomBar.LENGTH_LONG
import com.google.android.material.snackbar.Snackbar

class LoginActivity : AppCompatActivity(), LoginContract.View {
    private val binding: ActivityMainBinding by viewBinding()
    private var presenter: LoginPresenter = initPresenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        presenter = initPresenter()
        presenter.onAttach(this)
        binding.loginButton.setOnClickListener {
            presenter.onLogin(
                binding.loginEditText.text.toString(),
                binding.passwordEditText.text.toString(),
            )
        }
    }

    private fun initPresenter(): LoginPresenter =
        lastCustomNonConfigurationInstance as? LoginPresenter ?: LoginPresenter()

    override fun onRetainCustomNonConfigurationInstance(): Any = presenter

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

    override fun setErrorEmptyField() {
        binding.root.setBackgroundColor(resources.getColor(R.color.red_for_background, null))
        Snackbar.make(
            binding.root,
            resources.getString(R.string.error_text_empty_fields),
            LENGTH_LONG
        ).show()
    }

    override fun setErrorLogin() {
        binding.root.setBackgroundColor(resources.getColor(R.color.red_for_background, null))
        Snackbar.make(
            binding.root,
            resources.getString(R.string.error_text_login),
            LENGTH_LONG
        ).show()
    }

    override fun setErrorPassword() {
        binding.root.setBackgroundColor(resources.getColor(R.color.red_for_background, null))
        Snackbar.make(
            binding.root,
            resources.getString(R.string.error_text_password),
            LENGTH_LONG
        ).show()
    }

    override fun setErrorUnknown() {
        binding.root.setBackgroundColor(resources.getColor(R.color.red_for_background, null))
        Snackbar.make(
            binding.root,
            resources.getString(R.string.error_text_unknown),
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

    override fun getHandler(): Handler = Handler(Looper.getMainLooper())
}