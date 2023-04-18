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

        textViewBtn.setOnClickListener {
            val textViewPage = Intent(this, TextViewActivity::class.java)
            startActivity(textViewPage)
        }
    }
}