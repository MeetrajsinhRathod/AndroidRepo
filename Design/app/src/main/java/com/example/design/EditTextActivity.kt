package com.example.design

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class EditTextActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_text)



        validateEmailPassword()
    }

    fun validateEmailPassword() {
        val emailField = findViewById<TextInputEditText>(R.id.emailField)
        val emailLayout = findViewById<TextInputLayout>(R.id.emailTextInputLayout)
        val passwordField = findViewById<TextInputEditText>(R.id.passwordField)
        val passwordLayout = findViewById<TextInputLayout>(R.id.passwordTextInputLayout)

        emailField.setOnFocusChangeListener { view, focused ->
            if (!focused) {
                if(!Patterns.EMAIL_ADDRESS.matcher(emailField.text).matches()) {
                    emailLayout.helperText = "invalid email format"
                }
            } else {
                emailLayout.helperText = null
            }
        }

        passwordField.setOnFocusChangeListener { view, focused ->
            if (!focused){
                var password = passwordField.text.toString()
                if (password.length < 8) {
                    passwordLayout.helperText = "minimum length should be 8"
                }
                else if (!password.matches(".*[A-Z].*".toRegex())) {
                    passwordLayout.helperText = "must contain one upper case latter"
                }
                else if (!password.matches(".*[a-z].*".toRegex())) {
                    passwordLayout.helperText = "must contain one lower case latter"
                }
                else if (!password.matches(".*[@#$%^&+=-_].*".toRegex())) {
                    passwordLayout.helperText = "must contain one special character (@#$%^&+=-_)"
                }
            } else {
                passwordLayout.helperText = null
            }
        }
    }
}