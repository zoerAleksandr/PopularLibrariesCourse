package com.example.popularlibrariescourse.ui.registration

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.popularlibrariescourse.R
import com.example.popularlibrariescourse.app
import com.example.popularlibrariescourse.databinding.ActivityRegistrationBinding
import com.google.android.material.snackbar.Snackbar

class RegistrationActivity : AppCompatActivity() {
    private val binding: ActivityRegistrationBinding by viewBinding()
    private var viewModel: RegistrationViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)
        viewModel = initViewModel()
        binding.registrationButton.setOnClickListener {
            viewModel?.onRegistration(
                binding.loginEditText.text.toString(),
                binding.passwordEditText.text.toString(),
                binding.passwordConfirmationEditText.text.toString()
            )
        }

        viewModel?.showText?.subscribe {
            it?.let {
                Snackbar.make(
                    binding.root,
                    it,
                    Snackbar.LENGTH_SHORT
                ).show()
            }
        }

        viewModel?.stateRegistration?.subscribe { state ->
            when (state) {
                is StateRegistration.Success -> {
                    binding.containerLinearLayout.setBackgroundColor(
                        resources.getColor(
                            R.color.green_for_background,
                            null
                        )
                    )
                }
                is StateRegistration.Loading -> {
                    if (state.loading == true) {
                        binding.containerLinearLayout.alpha = 0.3f
                        binding.registrationButton.isEnabled = false
                        binding.progressBar.visibility = View.VISIBLE
                    } else {
                        binding.containerLinearLayout.alpha = 1f
                        binding.registrationButton.isEnabled = true
                        binding.progressBar.visibility = View.GONE
                    }
                }
                is StateRegistration.Error -> {
                    binding.containerLinearLayout.setBackgroundColor(
                        resources.getColor(
                            R.color.red_for_background,
                            null
                        )
                    )
                }
                else -> {}
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel?.stateRegistration?.unsubscribe()
        viewModel?.showText?.unsubscribe()
    }


    private fun initViewModel(): RegistrationViewModel =
        lastCustomNonConfigurationInstance as? RegistrationViewModel
            ?: RegistrationViewModel(app.loginUseCase)

    override fun onRetainCustomNonConfigurationInstance(): Any? = viewModel
}