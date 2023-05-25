package com.example.design

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.EditText
import androidx.transition.AutoTransition
import androidx.transition.TransitionManager
import com.example.design.databinding.ActivityOneCloudAccountSecurityBinding
import com.google.android.material.textfield.TextInputLayout

class OneCloudAccountSecurity : AppCompatActivity() {

    private lateinit var binding: ActivityOneCloudAccountSecurityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_one_cloud_account_security)
        binding = ActivityOneCloudAccountSecurityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpView()
    }

    private fun animate() {
        val transition = AutoTransition()
        transition.duration = 100
        TransitionManager.beginDelayedTransition(binding.root,transition)
    }

    private fun setUpView() {
        binding.btnBack.setOnClickListener { finish() }

        //make email field editable
        binding.btnEditMail.setOnClickListener {
            binding.apply {
                animate()
                confirmEmailGroup.visibility = View.VISIBLE
                btnEditMail.visibility = View.GONE
                tvEmail.isEnabled = true
                emailTextInputEditText.isEnabled = true
                tvConfirmEmail.isEnabled = true
                confirmEmailTextInputEditText.isEnabled = true
            }
        }

        //show multi factor authentication code
        binding.btnSetUpAuthentication.setOnClickListener {
            animate()
            binding.passcodeGroup.visibility = View.VISIBLE
            binding.btnSetUpAuthentication.visibility = View.GONE
        }

        //validating email fields
        binding.emailTextInputEditText.setOnFocusChangeListener { field, focused ->
            (field as? EditText)?.validateEmail(binding.emailTextInputLayout,focused)
        }
        binding.confirmEmailTextInputEditText.setOnFocusChangeListener { field, focused ->
            (field as? EditText)?.validateEmail(binding.confirmEmailTextInputLayout,focused)
        }

        //validating password fields
        binding.newPasswordTextInput.setOnFocusChangeListener { field, focused ->
            (field as? EditText)?.validatePassword(binding.newPasswordTextInputLayout,focused)
        }
        binding.confirmNewPasswordTextInput.setOnFocusChangeListener { field, focused ->
            (field as? EditText)?.validatePassword(binding.confirmNewPasswordTextInputLayout,focused)
        }
        binding.currentPasswordTextInput.setOnFocusChangeListener { field, focused ->
            (field as? EditText)?.validatePassword(binding.currentPasswordTextInputLayout,focused)
        }

        //save changes after validations
        binding.btnSave.setOnClickListener {
            if (binding.currentPasswordTextInput.text.isNullOrEmpty()) {
                binding.currentPasswordTextInputLayout.helperText = getString(R.string.this_field_is_required)
            } else {
                binding.currentPasswordTextInputLayout.helperText = null
                if (binding.confirmEmailGroup.visibility == View.VISIBLE) {
                    if (binding.emailTextInputEditText.text.toString() != binding.confirmEmailTextInputEditText.text.toString()) {
                        binding.confirmEmailTextInputLayout.error = getString(R.string.both_email_does_not_match)
                    } else {
                        binding.confirmEmailTextInputLayout.error = null
                    }
                }
                if (binding.passcodeGroup.visibility == View.VISIBLE) {
                    if (binding.passcodeTextInputEditText.text.toString() != getString(R.string.tfa_dumy_code)) {
                        binding.passcodeTextInputLayout.error = getString(R.string.passcode_does_not_match)
                    } else {
                        binding.passcodeTextInputLayout.error = null
                    }
                }
                if (binding.newPasswordTextInput.text.toString() != binding.confirmNewPasswordTextInput.text.toString()) {
                    binding.confirmEmailTextInputLayout.error = getString(R.string.both_passwords_does_not_match)
                } else {
                    binding.confirmEmailTextInputLayout.error = null
                }
            }
        }
    }
}

fun EditText.validateEmail(editTextInputLayout: TextInputLayout, focused: Boolean) {
    if (!focused) {
        if (!Patterns.EMAIL_ADDRESS.matcher(text).matches()) {
            editTextInputLayout.error = context.getString(R.string.invalid_email_format)
        }
    } else {
        editTextInputLayout.error = null
    }
}

fun EditText.validatePassword(passwordLayout: TextInputLayout, focused: Boolean) {
    if (!focused){
        val password = text.toString()
        if (password.length < 8) {
            passwordLayout.error = context.getString(R.string.minimum_length_should_be_8)
        } else if (!password.matches(".*[A-Z].*".toRegex())) {
            passwordLayout.error = context.getString(R.string.must_contain_one_upper_case_latter)
        } else if (!password.matches(".*[a-z].*".toRegex())) {
            passwordLayout.error = context.getString(R.string.must_contain_one_lower_case_latter)
        } else if (!password.matches(".*[0-9].*".toRegex())) {
            passwordLayout.error = context.getString(R.string.must_contain_one_number)
        }
    } else {
        passwordLayout.error = null
    }
}