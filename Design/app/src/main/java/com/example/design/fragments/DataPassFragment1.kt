package com.example.design.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentResultListener
import com.example.design.R
import com.example.design.databinding.FragmentDataPass1Binding

class DataPassFragment1 : Fragment() {

    private lateinit var binding: FragmentDataPass1Binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDataPass1Binding.inflate(inflater)

        parentFragmentManager.setFragmentResultListener("hostActivity", this) { _, bundle: Bundle ->
            val result = bundle.getString("Message")
            binding.etMessage.setText(result)
        }
        binding.btnSubmit.setOnClickListener {

            val bundle = Bundle()
            bundle.putString("Message",binding.etMessage.text.toString())
            parentFragmentManager.setFragmentResult("fragment1",bundle)
            binding.etMessage.text.clear()
        }

        return binding.root
    }
}