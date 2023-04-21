package com.example.design

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.widget.Button
import android.widget.Toast
import androidx.core.view.GravityCompat

class ToastActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_toast)

        val toastBtn1 = findViewById<Button>(R.id.toastBtn1)
        val toastBtn2 = findViewById<Button>(R.id.toastBtn2)

        toastBtn1.setOnClickListener {
            var toast1 = Toast.makeText(this, "Simple Toast", Toast.LENGTH_SHORT)
            toast1.setGravity(Gravity.TOP,0,0)
            toast1.show()
        }

        toastBtn2.setOnClickListener {
            var toast2 =  Toast(this)
            val customView = layoutInflater.inflate(R.layout.custom_toast_layout, findViewById(R.id.customToast))
            toast2.view = customView
            toast2.setGravity(Gravity.CENTER, 0,0)
            toast2.show()
        }
    }
}