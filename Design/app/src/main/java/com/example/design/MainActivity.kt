package com.example.design

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.design.adapters.UIWidgetsAdapter
import com.example.design.databinding.ActivityMainBinding
import com.example.design.model.UIWidgetsEnum

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val widgets = UIWidgetsEnum.values()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val adapter = UIWidgetsAdapter(widgets)
        binding.rvUIWidgets.adapter = adapter
        binding.rvUIWidgets.layoutManager = LinearLayoutManager(this)
    }
}