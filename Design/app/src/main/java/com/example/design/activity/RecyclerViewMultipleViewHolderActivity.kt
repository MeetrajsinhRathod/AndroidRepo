package com.example.design.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.design.R
import com.example.design.adapters.CountryCapitalListAdapter
import com.example.design.databinding.ActivityRecyclerViewMultipleViewHolderBinding
import com.example.design.model.Country
import java.util.TreeMap

class RecyclerViewMultipleViewHolderActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRecyclerViewMultipleViewHolderBinding
    private val countryList = arrayListOf(
        Country("India", R.drawable.india),
        Country("USA", R.drawable.usa),
        Country("UAE", R.drawable.uae),
        Country("Australia", R.drawable.australia),
        Country("Ukraine", R.drawable.ukraine),
        Country("China", R.drawable.china)
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