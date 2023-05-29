package com.example.design.fragments

import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.design.R
import com.example.design.databinding.FragmentTabLayout1Binding

class TabLayoutFragment1 : Fragment() {

    private lateinit var binding: FragmentTabLayout1Binding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTabLayout1Binding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

            val navigationController = Navigation.findNavController(view)

            binding.btnGoToNextFragment.setOnClickListener {
                navigationController.navigate(R.id.action_tabLayoutFragment1_to_tabLayoutFragment2)
            }
        super.onViewCreated(view, savedInstanceState)
    }
}