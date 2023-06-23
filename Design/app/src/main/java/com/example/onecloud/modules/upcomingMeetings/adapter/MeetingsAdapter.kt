package com.example.onecloud.modules.upcomingMeetings.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.design.R
import com.example.design.databinding.ItemUpcomingMeetingBinding
import com.example.design.databinding.ItemUpcomingMeetingDateBinding
import com.example.onecloud.customViews.PopUpFromLayout
import com.google.android.material.snackbar.Snackbar

class MeetingsAdapter: RecyclerView.Adapter<MeetingsAdapter.MeetingViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MeetingViewHolder {
        val binding = ItemUpcomingMeetingBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MeetingViewHolder(binding)
    }

    override fun getItemCount(): Int = 1

    override fun onBindViewHolder(holder: MeetingViewHolder, position: Int) {
        holder.setUpView()
    }

    inner class MeetingViewHolder(val binding: ItemUpcomingMeetingBinding) : RecyclerView.ViewHolder(binding.root) {

        fun setUpView() {
            binding.btnMoreOptions.setOnClickListener {
                val moreOptionPopup = PopUpFromLayout(
                    binding.btnMoreOptions.context,
                    R.layout.upcoming_meeting_pop_up,
                    binding.btnMoreOptions, -150, 0
                )
                moreOptionPopup.showPopup()
                moreOptionPopup.onItemSelectedListener { id ->
                    when (id) {
                        R.id.copy_link_menu_item -> {
                            Snackbar.make(
                                binding.tvOrgenizerName,
                                "Link Copied",
                                Snackbar.LENGTH_SHORT
                            ).show()
                        }
                        R.id.edit_menu_item -> {
                            Snackbar.make(
                                binding.tvOrgenizerName,
                                "Edit",
                                Snackbar.LENGTH_SHORT
                            ).show()
                        }
                        R.id.cancel_menu_item -> {
                            Snackbar.make(
                                binding.tvOrgenizerName,
                                "Cancel",
                                Snackbar.LENGTH_SHORT
                            ).show()
                        }
                    }
                }
            }
        }
    }

    inner class DateViewHolder(val binding: ItemUpcomingMeetingDateBinding) : RecyclerView.ViewHolder(binding.root) {

    }
}