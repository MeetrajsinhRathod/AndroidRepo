package com.example.design

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.example.design.databinding.ActivityAppBarLayoutBinding

class AppBarLayoutActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAppBarLayoutBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAppBarLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolBar)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val menuI = menuInflater.inflate(R.menu.menu_toolbar_items,menu)
        return true
    }
}