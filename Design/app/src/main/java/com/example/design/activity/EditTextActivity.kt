package com.example.design.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import com.example.design.databinding.ActivityEditTextBinding

class EditTextActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEditTextBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditTextBinding.inflate(layoutInflater)
        setContentView(binding.root)
        validateEmailPassword()
    }

    private fun validateEmailPassword() {
        binding.emailField.setOnFocusChangeListener { _, focused ->
            if (!focused) {
                if (!Patterns.EMAIL_ADDRESS.matcher(binding.emailField.text).matches()) {
                    binding.emailTextInputLayout.helperText = "invalid email format"
                }
            } else {
                binding.emailTextInputLayout.helperText = null
            }
        }

        binding.passwordField.setOnFocusChangeListener { _, focused ->
            if (!focused) {
                var password = binding.passwordField.text.toString()
                if (password.length < 8) {
                    binding.passwordTextInputLayout.helperText = "minimum length should be 8"
                } else if (!password.matches(".*[A-Z].*".toRegex())) {
                    binding.passwordTextInputLayout.helperText =
                        "must contain one upper case latter"
                } else if (!password.matches(".*[a-z].*".toRegex())) {
                    binding.passwordTextInputLayout.helperText =
                        "must contain one lower case latter"
                } else if (!password.matches(".*[@#$%^&+=-_].*".toRegex())) {
                    binding.passwordTextInputLayout.helperText =
                        "must contain one special character (@#$%^&+=-_)"
                }
            } else {
                binding.passwordTextInputLayout.helperText = null
            }
        }
    }
}