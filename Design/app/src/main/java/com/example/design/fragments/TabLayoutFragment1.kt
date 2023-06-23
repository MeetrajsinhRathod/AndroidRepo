package com.example.design.fragments

import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavDirections
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
            val action = TabLayoutFragment1Directions.actionTabLayoutFragment1ToTabLayoutFragment2(binding.etMessage.text.toString())
            navigationController.navigate(action)
        }
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onResume() {
        val message = arguments?.let { TabLayoutFragment1Args.fromBundle(it).message }
        binding.etMessage.setText(message)
        super.onResume()
    }
}