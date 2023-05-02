package com.example.design

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.Button
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

        binding.name = "Meetraj"
        binding.age = 21

        binding.btnUserDetails.setOnClickListener {
            Toast.makeText(this, "Name is : ${binding.name}, age : ${binding.age}", Toast.LENGTH_SHORT).show()
            Log.d("Onclick", "after do long time")
        }

    }
}