package com.example.popularlibrariescourse

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.popularlibrariescourse.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val binding: ActivityMainBinding by viewBinding()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding.btnLogin.setOnClickListener {
            Toast.makeText(this, "Тест", Toast.LENGTH_SHORT).show()
        }
    }
}