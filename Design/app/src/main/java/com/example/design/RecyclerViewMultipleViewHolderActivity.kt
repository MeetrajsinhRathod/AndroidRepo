package com.example.design

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import android.widget.Toast
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.design.adapters.CountryCapitalListAdapter
import com.example.design.adapters.CountryListAdapter
import com.example.design.databinding.ActivityRecyclerViewLinearBinding
import com.example.design.databinding.ActivityRecyclerViewMultipleViewHolderBinding
import com.example.design.model.Country
import com.google.android.material.snackbar.Snackbar

class RecyclerViewMultipleViewHolderActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRecyclerViewMultipleViewHolderBinding
    private val countryList = arrayListOf(
        Country("India",R.drawable.india),
        Country("USA",R.drawable.usa),
        Country("UAE",R.drawable.uae),
        Country("Australia",R.drawable.australia),
        Country("Ukraine",R.drawable.ukraine),
        Country("China",R.drawable.china)
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecyclerViewMultipleViewHolderBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val adapter = CountryCapitalListAdapter(countryList)
        binding.rvCountries.adapter = adapter
        binding.rvCountries.layoutManager = LinearLayoutManager(this)
        binding.rvCountries.addItemDecoration(DividerItemDecoration(this,RecyclerView.VERTICAL))
    }
}