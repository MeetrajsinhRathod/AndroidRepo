package com.example.design.Adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.design.Fragments.TabLayoutFragment1
import com.example.design.Fragments.TabLayoutFragment2
import com.example.design.Fragments.TabLayoutFragment3

class ViewPagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle): FragmentStateAdapter(fragmentManager, lifecycle) {
    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        return when(position) {
            0 -> TabLayoutFragment1()
            1 -> TabLayoutFragment2()
            else -> TabLayoutFragment3()
        }
    }
}