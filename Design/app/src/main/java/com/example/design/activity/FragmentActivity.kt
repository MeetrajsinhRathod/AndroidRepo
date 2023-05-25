package com.example.design.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.design.R
import com.example.design.databinding.ActivityFragmentBinding
import com.example.design.fragments.PopUpFragment
import com.example.design.fragments.TabLayoutFragment1
import com.example.design.fragments.TabLayoutFragment2

class FragmentActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFragmentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFragmentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnAddFragment.setOnClickListener {
            supportFragmentManager.beginTransaction().apply {
                replace(binding.fragmentHolder3.id, TabLayoutFragment1(),"1")
                //addToBackStack(null)
                commit()
            }
        }
        binding.btnRemoveFragment.setOnClickListener {
            supportFragmentManager.beginTransaction().apply {
                supportFragmentManager.findFragmentByTag("1")?.let { it1 -> remove(it1) }
                commit()
            }
        }

        binding.btnPopUpFragment.setOnClickListener {
            val popUpFragment = PopUpFragment()
            popUpFragment.show(supportFragmentManager,"popUp")
        }
    }
}