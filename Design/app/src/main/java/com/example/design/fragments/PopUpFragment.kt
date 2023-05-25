package com.example.design.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.design.R
import com.example.design.databinding.FragmentDataPass2Binding
import com.example.design.databinding.FragmentPopUpBinding

class PopUpFragment : DialogFragment() {

    private lateinit var binding: FragmentPopUpBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPopUpBinding.inflate(inflater, container, false)

        binding.btnDismiss.setOnClickListener {
            dismiss()
        }
        return binding.root
    }
}