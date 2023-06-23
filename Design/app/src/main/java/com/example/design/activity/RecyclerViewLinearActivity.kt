package com.example.design.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.design.R
import com.example.design.adapters.CountryListAdapter
import com.example.design.databinding.ActivityRecyclerViewLinearBinding
import com.example.design.model.Country

class RecyclerViewLinearActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRecyclerViewLinearBinding
    private val countryList = arrayListOf<Country>(
        Country("India", R.drawable.india),
        Country("USA", R.drawable.usa),
        Country("UAE", R.drawable.uae),
        Country("Australia", R.drawable.australia),
        Country("Ukraine", R.drawable.ukraine),
        Country("China", R.drawable.china),
        Country("India", R.drawable.india),
        Country("USA", R.drawable.usa),
        Country("UAE", R.drawable.uae),
        Country("Australia", R.drawable.australia),
        Country("Ukraine", R.drawable.ukraine),
        Country("China", R.drawable.china),
        Country("India", R.drawable.india),
        Country("USA", R.drawable.usa),
        Country("UAE", R.drawable.uae),
        Country("Australia", R.drawable.australia),
        Country("Ukraine", R.drawable.ukraine),
        Country("China", R.drawable.china),
        Country("India", R.drawable.india),
        Country("USA", R.drawable.usa),
        Country("UAE", R.drawable.uae),
        Country("Australia", R.drawable.australia),
        Country("Ukraine", R.drawable.ukraine),
        Country("China", R.drawable.china)
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecyclerViewLinearBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = CountryListAdapter(countryList)
        binding.rvCountries.adapter = adapter
        binding.rvCountries.layoutManager = LinearLayoutManager(this)
        binding.rvCountries.addItemDecoration(DividerItemDecoration(this, 1))

        fun filterList(text: String?){
            if (text != null) {
                val filteredList = ArrayList<Country>()
                for (country in countryList) {
                    if (country.countryName.lowercase().contains(text)) {
                        filteredList.add(country)
                    }
                }
                adapter.setFilteredList(filteredList)
            }
        }

        binding.searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }
            override fun onQueryTextChange(newText: String?): Boolean {
                filterList(newText)
                return true
            }
        })
    }

}