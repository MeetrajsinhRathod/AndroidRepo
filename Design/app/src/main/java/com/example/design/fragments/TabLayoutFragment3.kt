package com.example.design.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavOptions
import androidx.navigation.Navigation
import androidx.navigation.navOptions
import com.example.design.R
import com.example.design.databinding.FragmentTabLayout1Binding
import com.example.design.databinding.FragmentTabLayout3Binding

class TabLayoutFragment3() : Fragment() {

    private lateinit var binding: FragmentTabLayout3Binding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTabLayout3Binding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

            val navigationController = Navigation.findNavController(view)

            binding.btnGoToNextFragment.setOnClickListener {
                navigationController.popBackStack(R.id.tabLayoutFragment1, false)
                //val navOptions = NavOptions.Builder().setPopUpTo(R.id.tabLayoutFragment1,true).build()
                //navigationController.navigate(R.id.action_tabLayoutFragment3_to_tabLayoutFragment1, null, navOptions)
            }
        super.onViewCreated(view, savedInstanceState)
    }
}