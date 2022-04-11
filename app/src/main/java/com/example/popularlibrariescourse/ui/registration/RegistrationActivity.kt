package com.example.popularlibrariescourse.ui.registration

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.popularlibrariescourse.R
import com.example.popularlibrariescourse.app
import com.example.popularlibrariescourse.databinding.ActivityRegistrationBinding
import com.google.android.material.snackbar.Snackbar

class RegistrationActivity : AppCompatActivity(), RegistrationContract.View {
    private val binding: ActivityRegistrationBinding by viewBinding()
    private var presenter: RegistrationPresenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)
        presenter = initPresenter()
        presenter?.onAttach(this)
        binding.registrationButton.setOnClickListener {
            presenter?.onRegistration(
                binding.loginEditText.text.toString(),
                binding.passwordEditText.text.toString(),
                binding.passwordConfirmationEditText.text.toString()
            )
        }
    }

    private fun initPresenter(): RegistrationPresenter =
        lastCustomNonConfigurationInstance as? RegistrationPresenter
            ?: RegistrationPresenter(app.loginUseCase)

    override fun onRetainCustomNonConfigurationInstance(): Any? = presenter

    override fun setSuccess() {
        binding.containerLinearLayout.setBackgroundColor(
            resources.getColor(
                R.color.green_for_background,
                null
            )
        )
        Snackbar.make(
            binding.root,
            "Учетная запись создана",
            Snackbar.LENGTH_SHORT
        ).show()
    }

    override fun setError() {
        binding.containerLinearLayout.setBackgroundColor(
            resources.getColor(
                R.color.red_for_background,
                null
            )
        )
    }

    override fun setErrorRegistration(error: RegistrationError) {
        binding.containerLinearLayout.setBackgroundColor(
            resources.getColor(
                R.color.red_for_background,
                null
            )
        )
        Snackbar.make(
            binding.root,
            error.message,
            Snackbar.LENGTH_SHORT
        ).show()
    }

    override fun setProgress(progress: Boolean) {
        if (progress) {
            binding.containerLinearLayout.alpha = 0.3f
            binding.registrationButton.isEnabled = false
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.containerLinearLayout.alpha = 1f
            binding.registrationButton.isEnabled = true
            binding.progressBar.visibility = View.GONE
        }
    }
}