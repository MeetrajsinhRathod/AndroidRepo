package com.example.design

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.databinding.DataBindingUtil
import com.example.design.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnTextView.setOnClickListener {
            val textViewPage = Intent(this, TextViewActivity::class.java)
            startActivity(textViewPage)
        }

        binding.btnEditText.setOnClickListener {
            val editTextPage = Intent(this, EditTextActivity::class.java)
            startActivity(editTextPage)
        }

        binding.btnButtons.setOnClickListener {
            val buttonsPage = Intent(this, ButtonsActivity::class.java)
            startActivity(buttonsPage)
        }

        binding.btnRadio.setOnClickListener {
            val radioBtnPage = Intent(this, RadioBtnActivity::class.java)
            startActivity(radioBtnPage)
        }

        binding.btnToggleSwitch.setOnClickListener {
            val toggleSwitchPage = Intent(this, ToggleSwitchActivity::class.java)
            startActivity(toggleSwitchPage)
        }

        binding.btnImageView.setOnClickListener {
            val imageViewPage = Intent(this, ImageViewActivity::class.java)
            startActivity(imageViewPage)
        }

        binding.btnToast.setOnClickListener {
            val toastPage = Intent(this, ToastActivity::class.java)
            startActivity(toastPage)
        }

        binding.btnProgress.setOnClickListener {
            val progressPage = Intent(this, ProgressActivity::class.java)
            startActivity(progressPage)
        }

        binding.btnChips.setOnClickListener {
            val chipsPage = Intent(this, ChipsActivity::class.java)
            startActivity(chipsPage)
        }

        binding.btnSpinner.setOnClickListener {
            val spinnerPage = Intent(this, SpinnerActivity::class.java)
            startActivity(spinnerPage)
        }

        binding.btnDatePicker.setOnClickListener {
            val datePickerPage = Intent(this, DatePickerActivity::class.java)
            startActivity(datePickerPage)
        }

        binding.btnDataBinding.setOnClickListener {
            val dataBindingPage = Intent(this, DataBindingActivity::class.java)
            startActivity(dataBindingPage)
        }

        binding.btnFrameLayout.setOnClickListener {
            val frameLayoutPage = Intent(this, FrameLayoutActivity::class.java)
            startActivity(frameLayoutPage)
        }

        binding.btnFloatingActionButton.setOnClickListener {
            val floatingActionButtonPage = Intent(this, FloatingActionButtonActivity::class.java)
            startActivity(floatingActionButtonPage)
        }

        binding.btnSnackBar.setOnClickListener {
            val snackBarPage = Intent(this, SnackbarActivity::class.java)
            startActivity(snackBarPage)
        }

        binding.btnTabLayout.setOnClickListener {
            val tabLayoutPage = Intent(this, TabLayoutActivity::class.java)
            startActivity(tabLayoutPage)
        }
    }
}