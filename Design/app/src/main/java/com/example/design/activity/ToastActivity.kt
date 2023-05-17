package com.example.design.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.widget.Toast
import com.example.design.R
import com.example.design.databinding.ActivityToastBinding

class ToastActivity : AppCompatActivity() {
    private lateinit var binding: ActivityToastBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityToastBinding.inflate(layoutInflater)
        setContentView(binding.root)
        configureUI()
    }

    private fun configureUI() {
        binding.toastBtn1.setOnClickListener {
            var toast1 = Toast.makeText(this, "Simple Toast", Toast.LENGTH_SHORT)
            toast1.setGravity(Gravity.TOP,0,0)
            toast1.show()
        }

        binding.toastBtn2.setOnClickListener {
            var toast2 =  Toast(this)
            val customView = layoutInflater.inflate(R.layout.custom_toast_layout, findViewById(R.id.customToast))
            toast2.view = customView
            toast2.setGravity(Gravity.CENTER, 0,0)
            toast2.show()
        }
    }
}