package com.example.onecloud.modules.userProfile.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.design.R
import com.example.design.databinding.FragmentSetStatusBottomSheetBinding
import com.example.design.fragments.BottomSheetFragment

class SetStatusBottomSheetFragment : BottomSheetFragment() {

    private lateinit var binding: FragmentSetStatusBottomSheetBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSetStatusBottomSheetBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun getTheme(): Int = R.style.RoundedBottomSheet
}