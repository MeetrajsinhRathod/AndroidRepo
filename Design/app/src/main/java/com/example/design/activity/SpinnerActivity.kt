package com.example.design.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import com.example.design.R
import com.example.design.adapters.SpinnerItemAdapter
import com.example.design.databinding.ActivitySpinnerBinding

class SpinnerActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySpinnerBinding
    private val countryNames = arrayOf("india","australia", "china", "uae", "ukraine", "usa")
    private val countryFlags = arrayOf(
        R.drawable.india,
        R.drawable.australia,
        R.drawable.china,
        R.drawable.uae,
        R.drawable.ukraine,
        R.drawable.usa
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySpinnerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        configureUI()
    }

    private fun configureUI() {
        binding.countrySpinner.adapter = SpinnerItemAdapter(this, countryNames, countryFlags)

        val arrayAdapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, countryNames)
        binding.autoCompleteTextView.setAdapter(arrayAdapter)
    }
}