package com.example.onecloud.modules.userProfile.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.design.R
import com.example.design.databinding.FragmentSetStatusBottomSheetBinding
import com.example.design.fragments.BottomSheetFragment
import com.example.onecloud.modules.dashboard.activity.DashboardActivity
import com.example.onecloud.modules.userProfile.model.StatusData
import com.example.onecloud.modules.userProfile.viewModel.SetStatusMessageViewModel

class SetStatusBottomSheetFragment(
    private val userId: String,
    private val token: String,
    private val responseCallback: (StatusData?) -> Unit
) : BottomSheetFragment() {

    private lateinit var binding: FragmentSetStatusBottomSheetBinding
    private lateinit var viewModel: SetStatusMessageViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSetStatusBottomSheetBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this)[SetStatusMessageViewModel::class.java]
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnCloseDialog.setOnClickListener { dismiss() }
        binding.btnSaveStatus.setOnClickListener { setStatusMessage() }
        observeResponse()
    }

    private fun observeResponse() {
        viewModel.statusSuccessResponse.observe(viewLifecycleOwner) {
            responseCallback(it)
            dismiss()
        }
        viewModel.errorResponse.observe(viewLifecycleOwner) {
            (activity as DashboardActivity).showError(it.message ?: "Error Occurred. Please Try Again")
            dismiss()
        }
    }

    private fun setStatusMessage() {
        if (binding.etDescription.text.isNotEmpty()) {
            viewModel.setStatusInfo(
                userId,
                token,
                StatusData(binding.etDescription.text.toString())
            )
        }
    }
}