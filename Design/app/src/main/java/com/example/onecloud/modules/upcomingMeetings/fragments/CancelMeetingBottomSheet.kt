package com.example.onecloud.modules.upcomingMeetings.fragments

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.design.R
import com.example.design.databinding.FragmentCancelMeetingBottomSheetBinding
import com.example.design.fragments.BottomSheetFragment
import com.example.onecloud.modules.dashboard.activity.DashboardActivity
import com.example.onecloud.modules.upcomingMeetings.viewModel.CancelMeetingViewModel
import com.example.webService.api.ResponseType
import com.google.android.material.snackbar.Snackbar

class CancelMeetingBottomSheet(
    private val meetingId: Int,
    private val reloadData: () -> Unit
) : BottomSheetFragment() {

    private lateinit var binding: FragmentCancelMeetingBottomSheetBinding
    private lateinit var viewModel: CancelMeetingViewModel
    private lateinit var userToken: String
    private val sharedPref by lazy {
        activity?.getSharedPreferences(
            "application", Context.MODE_PRIVATE
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCancelMeetingBottomSheetBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnCloseDialog.setOnClickListener { dismiss() }
        viewModel = ViewModelProvider(this)[CancelMeetingViewModel::class.java]
        userToken = "Bearer ${sharedPref?.getString("userToken", "")}"
        binding.btnCancelMeeting.setOnClickListener {
            viewModel.cancelMeeting(meetingId, binding.etDescription.text.toString())
        }
        observeCancelMeetingResponse()
        setErrorResponseObserver()
    }

    override fun getTheme(): Int = R.style.RoundedBottomSheet

    private fun observeCancelMeetingResponse() {
        viewModel.meetingResponse.observe(viewLifecycleOwner) {
            if (it == ResponseType.Success) {
                reloadData()
            }
            dismiss()
        }
    }

    private fun setErrorResponseObserver() {
        viewModel.errorResponse.observe(this) {
            (activity as DashboardActivity).showError(it.message ?: "Error Occurred. Please Try Again")
            dismiss()
        }
    }
}