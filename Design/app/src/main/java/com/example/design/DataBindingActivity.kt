package com.example.design

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import com.example.design.databinding.ActivityDataBindingBinding

class DataBindingActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDataBindingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_data_binding)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_data_binding)
        configureUI()
    }

    private fun configureUI() {
        val user = User("Meetraj", 21)
        binding.name = user.name
        binding.age = user.age

        binding.btnUserDetails.setOnClickListener {
            Toast.makeText(this, "Name is : ${binding.name}, age : ${binding.age}", Toast.LENGTH_SHORT).show()
            Log.d("Onclick", "after do long time")
        }
    }
}

