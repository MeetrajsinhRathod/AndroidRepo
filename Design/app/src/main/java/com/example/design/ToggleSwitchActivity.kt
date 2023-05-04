package com.example.design

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Layout.Alignment
import android.view.Gravity
import android.view.View
import android.widget.Button
import android.widget.Switch
import android.widget.TextView
import android.widget.ToggleButton
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.SwitchCompat
import com.example.design.databinding.ActivityToggleSwitchBinding

class ToggleSwitchActivity : AppCompatActivity() {
    private lateinit var binding: ActivityToggleSwitchBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityToggleSwitchBinding.inflate(layoutInflater)
        setContentView(binding.root)
        configureUI()
    }

    private fun configureUI(){
        binding.leftAlign.setOnClickListener {
            binding.textToAlign.textAlignment = View.TEXT_ALIGNMENT_TEXT_START
            binding.textToAlign.text = binding.textToAlign.text
        }

        binding.centerAlign.setOnClickListener {
            binding.textToAlign.textAlignment = View.TEXT_ALIGNMENT_CENTER
            binding.textToAlign.text = binding.textToAlign.text
        }

        binding.rightAlign.setOnClickListener {
            binding.textToAlign.textAlignment = View.TEXT_ALIGNMENT_TEXT_END
            binding.textToAlign.text = binding.textToAlign.text
        }

        binding.nightModeSwitch.setOnCheckedChangeListener { _, checked ->
            if (checked) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
        }
    }
}