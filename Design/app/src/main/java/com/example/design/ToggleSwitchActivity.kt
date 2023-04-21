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

class ToggleSwitchActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_toggle_switch)

        val left = findViewById<Button>(R.id.leftAlign)
        val center = findViewById<Button>(R.id.centerAlign)
        val right = findViewById<Button>(R.id.rightAlign)
        val text = findViewById<TextView>(R.id.textToAlign)
        val nightModeSwitch = findViewById<SwitchCompat>(R.id.nightModeSwitch)

        left.setOnClickListener {
            text.textAlignment = View.TEXT_ALIGNMENT_TEXT_START
            text.text = text.text
        }

        center.setOnClickListener {
            text.textAlignment = View.TEXT_ALIGNMENT_CENTER
            text.text = text.text
        }

        right.setOnClickListener {
            text.textAlignment = View.TEXT_ALIGNMENT_TEXT_END
            text.text = text.text
        }

        nightModeSwitch.setOnCheckedChangeListener { _, checked ->
            if (checked) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
        }
    }
}