package com.example.design

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import com.example.design.adapters.CountryListAdapter
import com.example.design.databinding.ActivityRecyclerViewLinearBinding
import com.example.design.model.Country

class RecyclerViewGridActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRecyclerViewLinearBinding
    private val countryList = arrayListOf<Country>(
        Country("India",R.drawable.india),
        Country("USA",R.drawable.usa),
        Country("UAE",R.drawable.uae),
        Country("Australia",R.drawable.australia),
        Country("Ukraine",R.drawable.ukraine),
        Country("China",R.drawable.china),
        Country("India",R.drawable.india),
        Country("USA",R.drawable.usa),
        Country("UAE",R.drawable.uae),
        Country("Australia",R.drawable.australia),
        Country("Ukraine",R.drawable.ukraine),
        Country("China",R.drawable.china),
        Country("India",R.drawable.india),
        Country("USA",R.drawable.usa),
        Country("UAE",R.drawable.uae),
        Country("Australia",R.drawable.australia),
        Country("Ukraine",R.drawable.ukraine),
        Country("China",R.drawable.china),
        Country("India",R.drawable.india),
        Country("USA",R.drawable.usa),
        Country("UAE",R.drawable.uae),
        Country("Australia",R.drawable.australia),
        Country("Ukraine",R.drawable.ukraine),
        Country("China",R.drawable.china)
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecyclerViewLinearBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = CountryListAdapter(countryList)
        binding.rvCountries.adapter = adapter
        binding.rvCountries.layoutManager = GridLayoutManager(this,2)
        binding.rvCountries.addItemDecoration(DividerItemDecoration(this, 1))
    }
}