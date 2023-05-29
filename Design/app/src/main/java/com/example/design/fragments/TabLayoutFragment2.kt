package com.example.design.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.design.R
import com.example.design.databinding.FragmentTabLayout1Binding
import com.example.design.databinding.FragmentTabLayout2Binding

class TabLayoutFragment2 : Fragment() {
    private lateinit var binding: FragmentTabLayout2Binding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTabLayout2Binding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

            val navigationController = Navigation.findNavController(view)
            binding.btnGoToNextFragment.setOnClickListener {
                navigationController.navigate(R.id.action_tabLayoutFragment2_to_tabLayoutFragment3)
            }
        super.onViewCreated(view, savedInstanceState)
    }
}