package com.example.design

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Spinner
import com.example.design.Adapters.SpinnerItemAdapter

class SpinnerActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_spinner)

        val countrySpinner = findViewById<Spinner>(R.id.countrySpinner)
        val countryNames = arrayOf("india","australia", "china", "uae", "ukraine", "usa")
        val countryFlags = arrayOf(R.drawable.india,R.drawable.australia, R.drawable.china, R.drawable.uae, R.drawable.ukraine, R.drawable.usa)
        val autoCompleteTextView = findViewById<AutoCompleteTextView>(R.id.autoCompleteTextView)

        countrySpinner.adapter = SpinnerItemAdapter(this, countryNames, countryFlags)

        val arrayAdapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, countryNames)
        autoCompleteTextView.setAdapter(arrayAdapter)
    }
}