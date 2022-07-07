package com.example.popularlibrariescourse

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.popularlibrariescourse.data.RepositoryImpl
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
        binding.btnLogin.setOnClickListener {
            presenter.onLogin(
                binding.editLogin.text.toString(),
                binding.editPassword.text.toString(),
            )
        }
    }

    private fun initPresenter(): LoginPresenter =
        lastCustomNonConfigurationInstance as? LoginPresenter ?: LoginPresenter(
            this,
            RepositoryImpl()
        )

    override fun onRetainCustomNonConfigurationInstance(): Any = presenter

    override fun setSuccess() {
        binding.root.setBackgroundColor(resources.getColor(R.color.green_for_background, null))
        Snackbar.make(
            binding.root,
            resources.getString(R.string.text_snackbar_by_success),
            LENGTH_LONG
        ).show()
    }

    override fun setError(message: String) {
        binding.root.setBackgroundColor(resources.getColor(R.color.red_for_background, null))
        Snackbar.make(
            binding.root,
            message,
            LENGTH_LONG
        ).show()
    }

    override fun setProgress(progress: Boolean) {
        if (progress) {
            binding.container.alpha = 0.3f
            binding.btnLogin.isEnabled = false
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.container.alpha = 1f
            binding.btnLogin.isEnabled = true
            binding.progressBar.visibility = View.GONE
        }
    }

//    override fun getHandler(): Handler = Handler(Looper.getMainLooper())
}