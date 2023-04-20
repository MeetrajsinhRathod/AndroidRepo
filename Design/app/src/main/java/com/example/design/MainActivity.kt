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
    }
}