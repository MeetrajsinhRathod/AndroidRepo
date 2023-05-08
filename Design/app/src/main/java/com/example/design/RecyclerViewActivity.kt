package com.example.design

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.design.adapters.CountryListAdapter
import com.example.design.model.Country
import com.example.design.databinding.ActivityRecyclerViewBinding

class RecyclerViewActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRecyclerViewBinding
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
        Country("China",R.drawable.china))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecyclerViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = CountryListAdapter(countryList)
        binding.rvCountries.adapter = adapter
        binding.rvCountries.layoutManager = LinearLayoutManager(this)
    }
}