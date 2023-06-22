package com.example.design.activity

import android.app.SearchManager
import android.content.Context
import android.database.Cursor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.CursorAdapter
import android.widget.SimpleCursorAdapter
import android.widget.TextView
import androidx.appcompat.widget.SearchView
import com.example.design.R
import com.example.design.databinding.ActivitySearchViewBinding
import com.example.design.model.Country

class SearchViewActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySearchViewBinding
    private val fruitList =  arrayListOf(
        "Apple",
        "Banana",
        "Pineapple",
        "Orange",
        "Lychee",
        "Guava",
        "Peach",
        "Melon",
        "Watermelon",
        "Papaya",
        "Apple",
        "Banana",
        "Pineapple",
        "Orange",
        "Lychee",
        "Guava",
        "Peach",
        "Melon",
        "Watermelon",
        "Papaya")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchViewBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val arrayAdapter = ArrayAdapter(this,android.R.layout.simple_list_item_1,fruitList)
        binding.lvListView.adapter = arrayAdapter

        binding.searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }
            override fun onQueryTextChange(query: String?): Boolean {
                if (query != null) { arrayAdapter.filter.filter(query) }
                return true
            }
        })
    }
}