package com.example.onecloud.modules.upcomingMeetings.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.design.R
import com.example.design.databinding.FragmentUpcomingMeetingsBinding
import com.example.onecloud.modules.upcomingMeetings.adapter.MeetingsAdapter

class UpcomingMeetingsFragment : Fragment(R.layout.fragment_upcoming_meetings) {

    private lateinit var binding: FragmentUpcomingMeetingsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUpcomingMeetingsBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvUpcomingMeetings.adapter = MeetingsAdapter()
    }

}