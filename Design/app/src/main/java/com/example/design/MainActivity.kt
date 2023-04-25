package com.example.design

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val textViewBtn = findViewById<Button>(R.id.textViewBtn)
        val editTextBtn = findViewById<Button>(R.id.editTextBtn)
        val buttonsBtn = findViewById<Button>(R.id.buttonsBtn)
        val radioBtn = findViewById<Button>(R.id.radioBtn)
        val toggleSwitch = findViewById<Button>(R.id.toggleSwitch)
        val imageViewBtn = findViewById<Button>(R.id.imageViewBtn)
        val toastBtn = findViewById<Button>(R.id.toastBtn)
        val progressBtn = findViewById<Button>(R.id.progressBtn)
        val chipsBtn = findViewById<Button>(R.id.chipsBtn)

        textViewBtn.setOnClickListener {
            val textViewPage = Intent(this, TextViewActivity::class.java)
            startActivity(textViewPage)
        }

        editTextBtn.setOnClickListener {
            val editTextPage = Intent(this, EditTextActivity::class.java)
            startActivity(editTextPage)
        }

        buttonsBtn.setOnClickListener {
            val buttonsPage = Intent(this, ButtonsActivity::class.java)
            startActivity(buttonsPage)
        }

        radioBtn.setOnClickListener {
            val radioBtnPage = Intent(this, RadioBtnActivity::class.java)
            startActivity(radioBtnPage)
        }

        toggleSwitch.setOnClickListener {
            val toggleSwitchPage = Intent(this, ToggleSwitchActivity::class.java)
            startActivity(toggleSwitchPage)
        }

        imageViewBtn.setOnClickListener {
            val imageViewPage = Intent(this, ImageViewActivity::class.java)
            startActivity(imageViewPage)
        }

        toastBtn.setOnClickListener {
            val toastPage = Intent(this, ToastActivity::class.java)
            startActivity(toastPage)
        }

        progressBtn.setOnClickListener {
            val progressPage = Intent(this, ProgressActivity::class.java)
            startActivity(progressPage)
        }

        chipsBtn.setOnClickListener {
            val chipsPage = Intent(this, ChipsActivity::class.java)
            startActivity(chipsPage)
        }
    }
}