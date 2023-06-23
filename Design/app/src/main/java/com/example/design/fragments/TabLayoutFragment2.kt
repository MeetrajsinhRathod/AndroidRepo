package com.example.design.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.design.R
import com.example.design.databinding.FragmentTabLayout1Binding
import com.example.design.databinding.FragmentTabLayout2Binding

class TabLayoutFragment2 : Fragment() {
    private lateinit var binding: FragmentTabLayout2Binding
    private lateinit var navigationController: NavController
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTabLayout2Binding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        navigationController = Navigation.findNavController(view)
        val message = arguments?.let { TabLayoutFragment2Args.fromBundle(it).message }
        binding.etMessage.setText(message)

        binding.btnGoToNextFragment.setOnClickListener {
            navigationController.navigate(R.id.action_tabLayoutFragment2_to_tabLayoutFragment3)
        }
        backPressed()
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onPause() {

        super.onPause()
    }
    private fun backPressed() {
        activity?.onBackPressedDispatcher?.addCallback(viewLifecycleOwner, object:
            OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                val action = TabLayoutFragment2Directions.actionTabLayoutFragment2ToTabLayoutFragment1(binding.etMessage.text.toString())
                navigationController.navigate(action)
            }
        })
    }
}