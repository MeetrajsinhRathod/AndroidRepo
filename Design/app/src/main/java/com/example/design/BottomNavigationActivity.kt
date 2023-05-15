package com.example.design

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.design.adapters.ViewPagerAdapter
import com.example.design.databinding.ActivityBottomNavigationBinding
import com.example.design.fragments.TabLayoutFragment1
import com.example.design.fragments.TabLayoutFragment2
import com.example.design.fragments.TabLayoutFragment3

class BottomNavigationActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBottomNavigationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBottomNavigationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpView()
    }

    private fun setUpView() {

        val viewPagerAdapter = ViewPagerAdapter(supportFragmentManager, lifecycle)
        binding.viewPager.adapter = viewPagerAdapter

        binding.bottomNavigationView.setOnItemSelectedListener {
            when(it.itemId) {
                R.id.tab1 -> binding.viewPager.currentItem = 0
                R.id.tab2 -> binding.viewPager.currentItem = 1
                R.id.tab3 -> binding.viewPager.currentItem = 2
            }
            true
        }
        binding.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                when(position) {
                    0 -> binding.bottomNavigationView.selectedItemId = R.id.tab1
                    1 -> binding.bottomNavigationView.selectedItemId = R.id.tab2
                    2 -> binding.bottomNavigationView.selectedItemId = R.id.tab3
                }

            }
        })
    }
}