package com.example.design.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentResultListener
import com.example.design.R
import com.example.design.databinding.FragmentDataPass1Binding
import com.example.design.databinding.FragmentDataPass2Binding

class DataPassFragment2 : Fragment() {

    private lateinit var binding: FragmentDataPass2Binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDataPass2Binding.inflate(inflater)

        parentFragmentManager.setFragmentResultListener("fragment1", this) { _, bundle: Bundle ->
            val result = bundle.getString("Message")
            binding.etMessage.setText(result)
        }

        binding.btnSubmit.setOnClickListener {

            val bundle = Bundle()
            bundle.putString("Message",binding.etMessage.text.toString())
            parentFragmentManager.setFragmentResult("fragment2",bundle)
            binding.etMessage.text.clear()
        }

        return binding.root
    }
}