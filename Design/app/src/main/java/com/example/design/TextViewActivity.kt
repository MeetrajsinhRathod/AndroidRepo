package com.example.design

import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class TextViewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_text_view)

        val clickableText = findViewById<TextView>(R.id.clickableText)
        val longPressText = findViewById<TextView>(R.id.longPressText)

        clickableText.setOnClickListener {
            clickableText.setTextColor(Color.GREEN)
        }
        longPressText.setOnLongClickListener {
            Toast.makeText(applicationContext, "Long pressed", Toast.LENGTH_SHORT).show()
            longPressText.setTextColor(Color.MAGENTA)
            true
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()

    }
}