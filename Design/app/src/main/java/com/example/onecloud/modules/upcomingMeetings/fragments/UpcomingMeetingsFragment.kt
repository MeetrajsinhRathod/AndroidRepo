package com.example.onecloud.modules.upcomingMeetings.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.design.R
import com.example.design.databinding.FragmentUpcomingMeetingsBinding
import com.example.onecloud.modules.dashboard.activity.DashboardActivity
import com.example.onecloud.modules.upcomingMeetings.adapter.MeetingsAdapter
import com.example.onecloud.modules.upcomingMeetings.viewModel.UpcomingMeetingsViewModel
import com.google.android.material.snackbar.Snackbar

class UpcomingMeetingsFragment : Fragment(R.layout.fragment_upcoming_meetings) {

    private lateinit var binding: FragmentUpcomingMeetingsBinding
    private lateinit var viewModel: UpcomingMeetingsViewModel
    private lateinit var meetingsAdapter: MeetingsAdapter
    private var currentPage = 1
    private var totalPage = 1
    private var limit = 5

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
        meetingsAdapter = MeetingsAdapter(this::copyLink, this::editMeeting, this::cancelMeeting)
        binding.rvUpcomingMeetings.adapter = meetingsAdapter
        binding.rvUpcomingMeetings.layoutManager = LinearLayoutManager(context)
        binding.swipeRefreshLayout.setOnRefreshListener { reloadData() }
        binding.shimmerLayout.startShimmer()
        viewModel = ViewModelProvider(this)[UpcomingMeetingsViewModel::class.java]
        viewModel.getUpcomingMeetings(currentPage,limit)
        observeMeetingResponse()
        setUpPagination()
    }

    private fun reloadData() {
        currentPage = 1
        totalPage = 1
        viewModel.clearList()
        meetingsAdapter.clearList()
        binding.shimmerLayout.visibility = View.VISIBLE
        binding.shimmerLayout.startShimmer()
        viewModel.getUpcomingMeetings(currentPage,limit)
    }

    private fun setUpPagination() {
        binding.rvUpcomingMeetings.addOnScrollListener(object:
            RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val linearLayoutManager: LinearLayoutManager = recyclerView.layoutManager as LinearLayoutManager
                val lastVisibleItem: Int = linearLayoutManager.findLastCompletelyVisibleItemPosition()
                if (!meetingsAdapter.isLoading && lastVisibleItem >= totalPage + 1 && currentPage < totalPage) {
                    meetingsAdapter.showProgress()
                    currentPage += 1
                    viewModel.getUpcomingMeetings(currentPage, limit)
                }
            }
        })
    }

    private fun observeMeetingResponse() {
        viewModel.meetingResponse.observe(viewLifecycleOwner) {
            if (it != null) {
                meetingsAdapter.submitList(it)
            }
            meetingsAdapter.hideProgress()
            binding.apply {
                swipeRefreshLayout.isRefreshing = false
                shimmerLayout.stopShimmer()
                shimmerLayout.visibility = View.GONE
            }
        }
        viewModel.totalPage.observe(viewLifecycleOwner) {
            totalPage = it
        }
        viewModel.errorResponse.observe(viewLifecycleOwner) {
            (activity as DashboardActivity).showError(it.message ?: "Error Occurred. Please Try Again")
            currentPage -= 1
            meetingsAdapter.hideProgress()
            binding.shimmerLayout.stopShimmer()
            binding.shimmerLayout.visibility = View.GONE
        }
    }

    private fun copyLink() {
        Snackbar.make(
            binding.root,
            "Link Copied",
            Snackbar.LENGTH_SHORT
        ).show()
    }

    private fun editMeeting() {
        Snackbar.make(
            binding.root,
            "Edit Meeting",
            Snackbar.LENGTH_SHORT
        ).show()
    }

    private fun cancelMeeting(id: Int) {
        val cancelMeetingBottomSheet = CancelMeetingBottomSheet(id, this::reloadData)
        cancelMeetingBottomSheet.show(parentFragmentManager, "cancelMeetingSheet")
    }
}