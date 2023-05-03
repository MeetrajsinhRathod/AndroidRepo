package com.example.design

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.design.databinding.ActivityTabLayoutBinding

class TabLayoutActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTabLayoutBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityTabLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }
}