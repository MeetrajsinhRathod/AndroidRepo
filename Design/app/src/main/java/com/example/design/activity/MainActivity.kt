package com.example.design.activity

import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.design.adapters.UIWidgetsAdapter
import com.example.design.databinding.ActivityMainBinding
import com.example.design.helper.TimerReceiver
import com.example.design.model.UIWidgetsEnum

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var airplaneModeChangeReceiver: TimerReceiver
    private val widgets = UIWidgetsEnum.values()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val adapter = UIWidgetsAdapter(widgets)
        binding.rvUIWidgets.adapter = adapter
        binding.rvUIWidgets.layoutManager = LinearLayoutManager(this)

        airplaneModeChangeReceiver = TimerReceiver()
        IntentFilter(Intent.ACTION_AIRPLANE_MODE_CHANGED).apply {
            registerReceiver(airplaneModeChangeReceiver, this)
        }
    }

    override fun onDestroy() {
        unregisterReceiver(airplaneModeChangeReceiver)
        super.onDestroy()
    }
}